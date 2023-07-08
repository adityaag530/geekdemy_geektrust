package com.example.geektrust;

import com.example.geektrust.appConfig.ApplicationConfig;
import com.example.geektrust.command.CommandInvoker;
import com.example.geektrust.exceptions.NoSuchCommandException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    //mvn exec:java -Dexec.mainClass=com.example.geektrust.Main -Dexec.args="INPUT_FILE=sample_input/input1.txt"
    public static void main(String[] args) {
        //app config started
        ApplicationConfig applicationConfig = new ApplicationConfig();
        // commands registered
        CommandInvoker commandInvoker = applicationConfig.getCommandInvoker();

        //reading from file
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(args[0]));
            String line = reader.readLine();
            while (line != null) {
                List<String> tokens = Arrays.asList(line.split(" "));
                commandInvoker.executeCommand(tokens.get(0), tokens);
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException | NoSuchCommandException e) {
            e.printStackTrace();
        }
    }

}


