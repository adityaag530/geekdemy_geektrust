package com.example.geektrust.service;

import com.example.geektrust.entities.Programme;
import com.example.geektrust.repositories.ProgrammeRepository;
import com.example.geektrust.services.ProgrammeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;

/*
 * @author adityagupta
 * @date 10/07/23
 */
@DisplayName("ProgrammeServiceTest")
public class ProgrammeServiceTest {

    @Mock
    private ProgrammeRepository programmeRepositoryMock;


    @Test
    @DisplayName("add programe test")
    public void addProgrammeTest(){
        //Arrange
        ProgrammeService programmeServiceMock = new ProgrammeService(programmeRepositoryMock);
        String expected = "DEGREE";
        String actual = "";

        //Act
        actual = programmeServiceMock.program(Programme.DEGREE);

        //Assert
        Assertions.assertEquals(expected,actual);
    }
}
