package com.example.geektrust.constants;

import com.example.geektrust.entities.Constants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/*
 * @author adityagupta
 * @date 10/07/23
 */
@DisplayName("ConstantsTest")
public class ConstantsTest {

    @Test
    @DisplayName("Testing Constants Value")
    public void testingConstantsValue(){
        assertEquals(0.99f,Constants.DIPLOMA_DISCOUNT);
        assertNotEquals(0.97f, Constants.CERTIFICATION_DISCOUNT);
        assertEquals(4,Constants.MIN_NUMBER_OF_PROGRAMMES_FOR_MAX_DISCOUNT);
        assertEquals(2,Constants.MIN_NUMBER_OF_PROGRAMMES_FOR_MIN_DISCOUNT);
        assertNotEquals(0.2f,Constants.FACTOR_FOR_DEAL_G5_DISCOUNT);
    }
}
