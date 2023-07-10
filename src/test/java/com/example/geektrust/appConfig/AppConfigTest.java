package com.example.geektrust.appConfig;
/*
 * @author adityagupta
 * @date 10/07/23
 */

import com.example.geektrust.command.CommandInvoker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("AppConfigTest")
public class AppConfigTest {
    private ApplicationConfig applicationConfig;

    @BeforeEach
    void setup(){
        applicationConfig = new ApplicationConfig();
    }

    @Test
    @DisplayName("should Return CommandInvoker Via GetCommandInvoker()")
    public void shouldReturnCommandInvokerByGetCommandInvoker() {
        CommandInvoker commandInvoker=applicationConfig.getCommandInvoker();
        assertNotNull(commandInvoker);
    }
}
