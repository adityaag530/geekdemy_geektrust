package com.example.geektrust.appConfig;

import com.example.geektrust.command.*;
import com.example.geektrust.repositories.IProgrammeRepository;
import com.example.geektrust.repositories.ProgrammeRepository;

import com.example.geektrust.services.*;

public class ApplicationConfig {
    //repo objects creation
    private final IProgrammeRepository programmeRepository = new ProgrammeRepository();

    //service obj creation
    private final IProgrammeService programmeService = new ProgrammeService(programmeRepository) ;
    private final IBillService billService = new BillService(programmeService, programmeRepository);

    //command obj creation
    private final ICommand addProgrammeCommand = new AddProgrammeCommand(programmeService);
    private final ICommand applyCouponCommand = new ApplyCouponCommand(billService);
    private final ICommand printBillCommand = new PrintBillCommand(billService);
    private final ICommand addProMembership = new AddProMembershipCommand(billService);

    private final CommandInvoker commandInvoker = new CommandInvoker();

    public CommandInvoker getCommandInvoker(){

        commandInvoker.register("ADD_PROGRAMME",addProgrammeCommand);//ADD_PROGRAMME CERTIFICATION 1
        commandInvoker.register("APPLY_COUPON",applyCouponCommand);//APPLY_COUPON DEAL_G20
        commandInvoker.register("PRINT_BILL",printBillCommand);//PRINT_BILL
        commandInvoker.register("ADD_PRO_MEMBERSHIP",addProMembership);//PRINT_BILL

        return commandInvoker;
    }
}
