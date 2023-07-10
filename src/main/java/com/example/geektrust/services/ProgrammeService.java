package com.example.geektrust.services;

import com.example.geektrust.entities.Programme;
import com.example.geektrust.repositories.IProgrammeRepository;

public class ProgrammeService implements IProgrammeService{

    public IProgrammeRepository programmeRepository;

    public ProgrammeService(IProgrammeRepository programmeRepository) {
        this.programmeRepository = programmeRepository;
    }

    @Override
    public void addProgram(Programme p, int qty) {
        programmeRepository.addToList(p, qty);
    }

    @Override
    public void removeProgram(Programme p, int qty) {
        programmeRepository.removeFromList(p,qty);
    }

    public String program(Programme p){
        return p.toString();
    }
}
