package com.example.geektrust.command;

import com.example.geektrust.entities.Programme;
import com.example.geektrust.services.IProgrammeService;

import java.util.List;

public class AddProgrammeCommand implements ICommand{
    //ADD_PROGRAMME CERTIFICATION 1
    private IProgrammeService programmeService;

    public AddProgrammeCommand(IProgrammeService programmeService) {
        this.programmeService = programmeService;
    }

    @Override
    public void execute(List<String> tokens) {
        //ADD_PROGRAMME CERTIFICATION 1
        programmeService.addProgram(Programme.valueOf(tokens.get(1)), Integer.valueOf(tokens.get(2)));
    }
}

