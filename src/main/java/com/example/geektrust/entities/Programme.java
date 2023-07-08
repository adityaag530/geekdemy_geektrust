package com.example.geektrust.entities;

public enum Programme {
    CERTIFICATION(3000),
    DEGREE(5000),
    DIPLOMA(2500);

    public final int price;

    Programme(int price) {
        this.price = price;
    }

    public static boolean isValidProgramme(String programme){
        if( programme.equals(String.valueOf(CERTIFICATION)) || programme.equals(String.valueOf(DEGREE)) || programme.equals(String.valueOf(DIPLOMA)) ){
            return true;
        }else{
            return false;
        }
    }
}
