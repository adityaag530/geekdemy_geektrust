package com.example.geektrust.commands;

import com.example.geektrust.command.AddProMembershipCommand;
import com.example.geektrust.command.AddProgrammeCommand;
import com.example.geektrust.command.ApplyCouponCommand;
import com.example.geektrust.command.CommandInvoker;
import com.example.geektrust.exceptions.NoSuchCommandException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyList;

/*
 * @author adityagupta
 * @date 10/07/23
 */
@DisplayName("CommandInvokerTest")
@ExtendWith(MockitoExtension.class)
public class CommandInvokerTest {

    private CommandInvoker commandInvoker;
    @Mock
    AddProgrammeCommand addProgrammeCommandMock;

    @Mock
    AddProMembershipCommand addProMembershipCommandMock;

    @Mock
    ApplyCouponCommand applyCouponCommandMock;

    @BeforeEach
    void setup(){
        commandInvoker = new CommandInvoker();
        commandInvoker.register("ADD_PROGRAMME",addProgrammeCommandMock);//ADD_PROGRAMME CERTIFICATION 1
        commandInvoker.register("APPLY_COUPON",applyCouponCommandMock);//APPLY_COUPON DEAL_G20
        commandInvoker.register("ADD_PRO_MEMBERSHIP",addProMembershipCommandMock);//PRINT_BILL
    }

    @Test
    @DisplayName("executeCommand method Should Execute Command Given CommandName and List of tokens")
    public void executeCommand_GivenNameAndTokens_ShouldExecuteCommand() {
        commandInvoker.executeCommand("ADD_PROGRAMME",anyList());
        commandInvoker.executeCommand("APPLY_COUPON",anyList());
        commandInvoker.executeCommand("ADD_PRO_MEMBERSHIP",anyList());

    }

    @Test
    @DisplayName("executeCommand method Should Throw Exception Given Incorrect CommandName and List of tokens")
    public void executeCommand_GivenIncorrectNameAndTokens_ShouldThrowException() {
        //Act and Assert
        Assertions.assertThrows(NoSuchCommandException.class,() -> commandInvoker.executeCommand("RANDOM-COMMAND",new ArrayList<String>()));

    }


}
