package com.example.geektrust.repositories;

import com.example.geektrust.entities.Programme;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProgrammeRepository implements IProgrammeRepository {


    private HashMap<Programme, Integer> listOfProgramm;
    public HashMap<Programme, Integer> getListOfProgramm() {
        return listOfProgramm;
    }

    public ProgrammeRepository() {
        listOfProgramm = new HashMap<>();
    }

    public void addToList(Programme p, int qty){
        if(listOfProgramm.containsKey(p)){
            listOfProgramm.put(p, listOfProgramm.get(p)+1);
        }else{
            listOfProgramm.put(p, qty);
        }
    }

    public void removeFromList (Programme p, int qty){
        if(listOfProgramm.containsKey(p)){
            listOfProgramm.put(p, listOfProgramm.get(p)-1);
        }else{
            throw new RuntimeException("Invalid Operation");
        }
    }

    public int numberOfProgrammes(){
        int count = 0;
        for(Map.Entry<Programme, Integer> m : listOfProgramm.entrySet()){
            count += m.getValue();
        }
        return count;
    }

    @Override
    public Programme save(Programme entity) {
        return null;
    }

    @Override
    public List<Programme> findAll() {
        return null;
    }

    @Override
    public Optional<Programme> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public void delete(Programme entity) {

    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public long count() {
        return 0;
    }
}
