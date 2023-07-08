package com.example.geektrust.entities;

import org.junit.jupiter.api.Test;

import static junit.framework.Assert.assertEquals;

public class CouponTest {
    @Test
    public void checkInvalidCouponTest(){

        boolean expected = false;
        boolean actual = Coupon.isValidCoupon("ASDFAS");

        assertEquals(expected, actual);

    }
    @Test
    public void checkEmptyCouponTest(){

        boolean expected = false;
        boolean actual = Coupon.isValidCoupon("");

        assertEquals(expected, actual);

    }
    @Test
    public void checkValidCouponTest(){

        boolean expected = true;
        boolean actual = Coupon.isValidCoupon("DEAL_G5");

        assertEquals(expected, actual);

    }
}
