package org.project;

/**
 * Main class invoked upon launching the executable jar
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

