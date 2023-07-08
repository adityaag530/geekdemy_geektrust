package com.example.geektrust.entities;

import org.junit.jupiter.api.Test;

import static junit.framework.Assert.assertEquals;

public class ProgrammeTest {

    @Test
    public void checkInvalidProgrammeTest(){

        boolean expected = false;
        boolean actual = Programme.isValidProgramme("INTERMEDIATE");

        assertEquals(expected, actual);

    }
    @Test
    public void checkEmptyProgrammeTest(){

        boolean expected = false;
        boolean actual = Programme.isValidProgramme("");

        assertEquals(expected, actual);

    }
    @Test
    public void checkValidProgrammeTest(){

        boolean expected = true;
        boolean actual = Programme.isValidProgramme("DIPLOMA");

        assertEquals(expected, actual);

    }
}
