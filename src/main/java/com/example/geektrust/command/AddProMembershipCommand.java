package com.example.geektrust.command;

import com.example.geektrust.services.IBillService;

import java.util.List;

public class AddProMembershipCommand implements ICommand{

    private IBillService billService;

    public AddProMembershipCommand(IBillService billService) {
        this.billService = billService;
    }

    @Override
    public void execute(List<String> tokens) {
        billService.addProMem();
    }
}
