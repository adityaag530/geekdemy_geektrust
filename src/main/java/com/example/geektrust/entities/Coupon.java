package com.example.geektrust.entities;

public enum Coupon {
    B4G1,
    DEAL_G20,
    DEAL_G5;

    public static boolean isValidCoupon(String coupon){
        if( coupon.equals(String.valueOf(B4G1)) || coupon.equals(String.valueOf(DEAL_G5)) || coupon.equals(String.valueOf(DEAL_G20)) ){
            return true;
        }else{
            return false;
        }
    }
}
