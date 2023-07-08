package com.example.geektrust.command;

import com.example.geektrust.services.IBillService;

import java.util.List;

public class ApplyCouponCommand implements ICommand {
    private IBillService billService;
    public ApplyCouponCommand(IBillService billService) {
        this.billService = billService;
    }

    @Override
    public void execute(List<String> tokens) {
        //tokens = APPLY_COUPON DEAL_G20
        billService.setCoupon(tokens.get(1));
    }
}
