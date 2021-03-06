package org.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the cronparser
 */
public class CronParserTest {
    /**
     * Test to verify that a valid cron expression throws no exceptions on initialisation
     */
    @Test
    public void TestValidCron() {
        String[] args = new String[6];
        args[0] = "*/15";
        args[1] = "0";
        args[2] = "1,15";
        args[3] = "*";
        args[4] = "1-5";
        args[5] = "/usr/bin/find";

        assertDoesNotThrow(() -> {
            new Cron(args);
        });
    }

    /**
     * Test to verify that an empty cron expression throws EmptyInputException
     *
     * @see EmptyInputException
     */
    @Test
    public void TestEmptyCron() {
        String[] args = null;

        Exception exception = assertThrows(EmptyInputException.class, () -> {
            new Cron(args);
        });
        String expectedMessage = "Cron expression cannot be empty or null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Test to verify that an invalid cron expression throws InvalidInputException
     *
     * @see InvalidInputException
     */
    @Test
    public void TestInvalidCron() {
        String[] args = new String[6];
        args[0] = "*/15";
        args[1] = "a-b";
        args[2] = "1,15";
        args[3] = "*";
        args[4] = "1-5";
        args[5] = "/usr/bin/find";

        Cron cron = null;
        try {
            cron = new Cron(args);
        } catch (EmptyInputException emptyInputException) {
            emptyInputException.printStackTrace();
        }

        Cron finalCron = cron;
        Exception exception = assertThrows(InvalidInputException.class, () -> {
            finalCron.process(0, 23, "a-b");
        });
        String expectedMessage = "Cron expression is invalid.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

}
