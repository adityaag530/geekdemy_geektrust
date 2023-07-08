package com.example.geektrust.services;

import com.example.geektrust.entities.Bill;
import com.example.geektrust.entities.Constants;
import com.example.geektrust.entities.Coupon;
import com.example.geektrust.entities.Programme;
import com.example.geektrust.repositories.IProgrammeRepository;

import java.util.ArrayList;
import java.util.Map;

public class BillService implements IBillService{

    private IProgrammeRepository programmeRepository;
    private IProgrammeService programmeService;
    private Bill bill;
    public BillService(IProgrammeService programmeService, IProgrammeRepository programmeRepository) {
        this.programmeRepository = programmeRepository;
        this.programmeService = programmeService;
        this.bill = new Bill();
    }
    @Override
    public void addProMem() {
        bill.setProMembershipAdded(true);
        bill.setProMemFee(Constants.proMemFee);
        this.calcProMemDiscount();
//        System.out.println("Pro-Membership Added!!");
    }


    public void applyCoupon(String ca){
        float disc = 0;
        if(programmeRepository.numberOfProgrammes() >= Constants.minNumberOfProgrammesForMaxDiscount){
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
            if(bill.isProMembershipAdded()) disc = disc * Constants.diplomaDiscount;
        }else if(programmeRepository.getListOfProgramm().containsKey(Programme.CERTIFICATION)){
            programmeRepository.removeFromList(Programme.CERTIFICATION, 1);
            disc = bill.getDiscount() + Programme.CERTIFICATION.price;
            if(bill.isProMembershipAdded()) disc = disc * Constants.certificationDiscount;
        }else if(programmeRepository.getListOfProgramm().containsKey(Programme.DEGREE)){
            programmeRepository.removeFromList(Programme.DEGREE, 1);
            disc = bill.getDiscount() + Programme.DEGREE.price;
            if(bill.isProMembershipAdded()) disc = disc * Constants.degreeDiscount;
        }
        return disc;
    }

    public float checkForOtherCoupon(float disc, String ca){
        if(bill.getSubTotal()>=Constants.minPriceForOtherDiscount){
            disc = getDiscountForMinPrice(disc, ca);
        }else if(programmeRepository.numberOfProgrammes() >= Constants.minNumberOfProgrammesForMinDiscount && ca.equals(String.valueOf(Coupon.DEAL_G5))){
            disc =  Constants.factorForDeal_G5_Discount * bill.getSubTotal();
            bill.setCa("DEAL_G5");
        }else{
            bill.setCa("NONE");
        }
        return disc;
    }

    public float getDiscountForMinPrice(float disc, String ca) {
        if(ca.equals(String.valueOf(Coupon.DEAL_G20))){
            disc = Constants.factorForDeal_G20_Discount * bill.getSubTotal();
            bill.setCa("DEAL_G20");
        }else if(ca.equals(String.valueOf(Coupon.DEAL_G5))){
            disc =  Constants.factorForDeal_G5_Discount * bill.getSubTotal();
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
                        proDisc += m.getValue() * m.getKey().price * (1 - Constants.certificationDiscount);
                        break;
                    case "DEGREE":
                        proDisc += m.getValue() * m.getKey().price * (1 - Constants.degreeDiscount);
                        break;
                    case "DIPLOMA":
                        proDisc += m.getValue() * m.getKey().price * (1 - Constants.diplomaDiscount);
                        break;
                }
            }
        }
        bill.setProDiscount(proDisc);
    }

    public void addEnrollmentFee(){
        if(bill.getSubTotal() < Constants.minPriceForEnrollFee) {
            bill.setEnrollFee(Constants.enrollFee);
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
