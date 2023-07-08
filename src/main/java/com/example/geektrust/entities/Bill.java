package com.example.geektrust.entities;

public class Bill {

    private Float subTotal = 0f;
    private Float total = 0f;
    private Float discount= 0f;
    private Float proDiscount= 0f;
    private Float enrollFee = 0f;
    private Float proMemFee = 0f;
    private String ca = "NONE";

    public Float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Float subTotal) {
        this.subTotal = subTotal;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Float getProDiscount() {
        return proDiscount;
    }

    public void setProDiscount(Float proDiscount) {
        this.proDiscount = proDiscount;
    }

    public Float getEnrollFee() {
        return enrollFee;
    }

    public void setEnrollFee(Float enrollFee) {
        this.enrollFee = enrollFee;
    }

    public Float getProMemFee() {
        return proMemFee;
    }

    public void setProMemFee(Float proMemFee) {
        this.proMemFee = proMemFee;
    }

    public String getCa() {
        return ca;
    }

    public void setCa(String ca) {
        this.ca = ca;
    }

    public boolean isProMembershipAdded() {
        return proMembershipAdded;
    }

    public void setProMembershipAdded(boolean proMembershipAdded) {
        this.proMembershipAdded = proMembershipAdded;
    }

    private boolean proMembershipAdded = false;


}
