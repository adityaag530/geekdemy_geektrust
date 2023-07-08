package com.example.geektrust.repositories;

import com.example.geektrust.entities.Programme;

import java.util.HashMap;

public interface IProgrammeRepository extends CRUDRepository<Programme,String>{
    public void addToList(Programme p, int qty);

    public void removeFromList(Programme p, int qty);

    public HashMap<Programme, Integer> getListOfProgramm();

    public int numberOfProgrammes();
}
