package com.example.geektrust.command;

import com.example.geektrust.services.IBillService;

import java.util.List;

public class PrintBillCommand implements ICommand{
    private IBillService billService;
    public PrintBillCommand(IBillService billService) {
        this.billService = billService;
    }

    @Override
    public void execute(List<String> tokens) {
        this.billService.printBill();
    }
}
