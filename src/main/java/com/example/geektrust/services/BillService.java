package com.example.geektrust.services;

import com.example.geektrust.entities.Bill;
import com.example.geektrust.entities.Constants;
import com.example.geektrust.entities.Coupon;
import com.example.geektrust.entities.Programme;
import com.example.geektrust.repositories.IProgrammeRepository;

import java.util.Map;

public class BillService implements IBillService{

    private final IProgrammeRepository programmeRepository;

    private final Bill bill;
    public BillService(IProgrammeRepository programmeRepository) {
        this.programmeRepository = programmeRepository;
        this.bill = new Bill();
    }
    @Override
    public void addProMem() {
        bill.setProMembershipAdded(true);
        bill.setProMemFee(Constants.PRO_MEM_FEE);
        this.calcProMemDiscount();
//        System.out.println("Pro-Membership Added!!");
    }


    public void applyCoupon(String ca){
        float disc = 0;
        if(programmeRepository.numberOfProgrammes() >= Constants.MIN_NUMBER_OF_PROGRAMMES_FOR_MAX_DISCOUNT){
            bill.setCa(String.valueOf(Coupon.B4G1));
            disc = checkForMaxCoupon(disc);
        }else {
            disc = checkForOtherCoupon(disc, ca);
        }
        bill.setDiscount(disc);
    }

    public float checkForMaxCoupon(float disc){
        if(programmeRepository.getListOfProgramm().containsKey(Programme.DIPLOMA)){
            programmeRepository.removeFromList(Programme.DIPLOMA, 1);
            disc = bill.getDiscount() + Programme.DIPLOMA.price;
            if(bill.isProMembershipAdded()) disc = disc * Constants.DIPLOMA_DISCOUNT;
        }else if(programmeRepository.getListOfProgramm().containsKey(Programme.CERTIFICATION)){
            programmeRepository.removeFromList(Programme.CERTIFICATION, 1);
            disc = bill.getDiscount() + Programme.CERTIFICATION.price;
            if(bill.isProMembershipAdded()) disc = disc * Constants.CERTIFICATION_DISCOUNT;
        }else if(programmeRepository.getListOfProgramm().containsKey(Programme.DEGREE)){
            programmeRepository.removeFromList(Programme.DEGREE, 1);
            disc = bill.getDiscount() + Programme.DEGREE.price;
            if(bill.isProMembershipAdded()) disc = disc * Constants.DEGREE_DISCOUNT;
        }
        return disc;
    }

    public float checkForOtherCoupon(float disc, String ca){
        if(bill.getSubTotal()>=Constants.MIN_PRICE_FOR_OTHER_DISCOUNT){
            disc = getDiscountForMinPrice(disc, ca);
        }else if(programmeRepository.numberOfProgrammes() >= Constants.MIN_NUMBER_OF_PROGRAMMES_FOR_MIN_DISCOUNT && ca.equals(String.valueOf(Coupon.DEAL_G5))){
            disc =  Constants.FACTOR_FOR_DEAL_G5_DISCOUNT * bill.getSubTotal();
            bill.setCa("DEAL_G5");
        }else{
            bill.setCa("NONE");
        }
        return disc;
    }

    public float getDiscountForMinPrice(float disc, String ca) {
        if(ca.equals(String.valueOf(Coupon.DEAL_G20))){
            disc = Constants.FACTOR_FOR_DEAL_G20_DISCOUNT * bill.getSubTotal();
            bill.setCa("DEAL_G20");
        }else if(ca.equals(String.valueOf(Coupon.DEAL_G5))){
            disc =  Constants.FACTOR_FOR_DEAL_G5_DISCOUNT * bill.getSubTotal();
            bill.setCa("DEAL_G5");
        }
        return disc;
    }

    public void calSubTotal(){
        float subTot = 0;
        for(Map.Entry<Programme, Integer> m: programmeRepository.getListOfProgramm().entrySet())
            subTot += m.getValue() * m.getKey().price;

        if(bill.isProMembershipAdded()){
            this.calcProMemDiscount();
            subTot = subTot + bill.getProMemFee() - bill.getProDiscount();
        }
        bill.setSubTotal(subTot);
        this.addEnrollmentFee();
    }

    public void calcProMemDiscount(){
        float proDisc = 0;
        if(bill.isProMembershipAdded()) {
            for (Map.Entry<Programme, Integer> m : programmeRepository.getListOfProgramm().entrySet()) {
                switch (String.valueOf(m.getKey())) {
                    case "CERTIFICATION":
                        proDisc += m.getValue() * m.getKey().price * (1 - Constants.CERTIFICATION_DISCOUNT);
                        break;
                    case "DEGREE":
                        proDisc += m.getValue() * m.getKey().price * (1 - Constants.DEGREE_DISCOUNT);
                        break;
                    case "DIPLOMA":
                        proDisc += m.getValue() * m.getKey().price * (1 - Constants.DIPLOMA_DISCOUNT);
                        break;
                }
            }
        }
        bill.setProDiscount(proDisc);
    }

    public void addEnrollmentFee(){
        if(bill.getSubTotal() < Constants.MIN_PRICE_FOR_ENROLL_FEE) {
            bill.setEnrollFee(Constants.ENROLL_FEE);
        }
    }

    @Override
    public void setCoupon(String coupon) {
        bill.setCa(coupon);
    }

    @Override
    public void printBill() {
        this.calSubTotal();
        this.applyCoupon(bill.getCa());
        this.calcTotal();
        System.out.printf("SUB_TOTAL %.2f\n", bill.getSubTotal());
        System.out.printf("COUPON_DISCOUNT %s %.2f\n", bill.getCa(), bill.getDiscount() );
        System.out.printf("TOTAL_PRO_DISCOUNT %.2f\n", bill.getProDiscount() );
        System.out.printf("PRO_MEMBERSHIP_FEE %.2f\n", bill.getProMemFee() );
        System.out.printf("ENROLLMENT_FEE %.2f\n", bill.getEnrollFee() );
        System.out.printf("TOTAL %.2f\n", bill.getTotal() );

    }

    public void calcTotal(){
        bill.setTotal( bill.getSubTotal() - bill.getDiscount() + bill.getEnrollFee());
    }

}
