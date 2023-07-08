package com.example.geektrust.command;

import java.util.List;

public interface ICommand {
        void execute(List<String> tokens);
}
