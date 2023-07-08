package com.example.geektrust.services;

import com.example.geektrust.entities.Programme;

public interface IProgrammeService {
    public void addProgram(Programme p, int qty);
    public void removeProgram(Programme p, int qty);
}
