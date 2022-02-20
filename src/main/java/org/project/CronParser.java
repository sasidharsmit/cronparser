package org.project;

/**
 * A cron parser that takes a cron expression as input and prints the expanded
 * times/dates for the time/date fields, along with the command
 *
 * @author Sasidhar Sekar
 * @version 1.0-SNAPSHOT
 */
public class CronParser {
    /**
     * This is the main method. Creates a new cron object
     * and invokes the printAll() method to solve the specified problem
     *
     * @param args Valid cron expression
     */
    public static void main(String[] args) {
        Cron cron = null;
        try {
            cron = new Cron(args);
        } catch (EmptyInputException emptyInputException) {
            emptyInputException.printStackTrace();
        }
        cron.printAll();
    }
}

