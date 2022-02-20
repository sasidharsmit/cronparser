package org.project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * A blueprint for the cron object. Contains a constructor to initialise the object with a
 * cron expression and has convenience methods to print expanded values for each field (minute,
 * hour, day of month, month, day of week, command) individually and for all fields together
 */
public class Cron {

    LinkedHashMap<String, String> cronMap = new LinkedHashMap<String, String>();

    /**
     * Initialises the Cron object with the provided cron expression
     *
     * @param cronString Valid cron expression
     * @Exception EmptyInputException if input is null
     * @see EmptyInputException
     */
    public Cron(String[] cronString) throws EmptyInputException {
        if (cronString == null) {
            throw new EmptyInputException("Cron expression cannot be empty or null");
        }
        this.cronMap.put("minute", cronString[0]);
        this.cronMap.put("hour", cronString[1]);
        this.cronMap.put("day of month", cronString[2]);
        this.cronMap.put("month", cronString[3]);
        this.cronMap.put("day of week", cronString[4]);
        this.cronMap.put("command", cronString[5]);
    }

    /**
     * This method prints the expanded time values for each time field in the specified cron expression
     */
    public void printAll() {
        printMinute();
        printHour();
        printDayOfMonth();
        printMonth();
        printDayOfWeek();
        printCommand();
    }

    /**
     * This method prints the expanded values for the "minute" field in the specified cron expression
     */
    public void printMinute() {
        try {
            format("minute", process(0, 59, this.cronMap.get("minute")));
        } catch (InvalidInputException invalidInputException) {
            invalidInputException.printStackTrace();
        }
    }

    /**
     * This method prints the expanded values for the "hour" field in the specified cron expression
     */
    public void printHour() {
        try {
            format("hour", process(0, 23, this.cronMap.get("hour")));
        } catch (InvalidInputException invalidInputException) {
            invalidInputException.printStackTrace();
        }
    }

    /**
     * This method prints the expanded values for the "day of month" field in the specified cron expression
     */
    public void printDayOfMonth() {
        try {
            format("dayOfMonth", process(1, 31, this.cronMap.get("day of month")));
        } catch (InvalidInputException invalidInputException) {
            invalidInputException.printStackTrace();
        }
    }

    /**
     * This method prints the expanded values for the "month" field in the specified cron expression
     */
    public void printMonth() {
        try {
            format("month", process(1, 12, this.cronMap.get("month")));
        } catch (InvalidInputException invalidInputException) {
            invalidInputException.printStackTrace();
        }
    }

    /**
     * This method prints the expanded values for the "day of week" field in the specified cron expression
     */
    public void printDayOfWeek() {
        try {
            format("day of week", process(0, 6, this.cronMap.get("day of week")));
        } catch (InvalidInputException invalidInputException) {
            invalidInputException.printStackTrace();
        }
    }

    /**
     * This method prints the value for the "command" field in the specified cron expression
     */
    public void printCommand() {
        try {
            format("command", process(0, 0, this.cronMap.get("command")));
        } catch (InvalidInputException invalidInputException) {
            invalidInputException.printStackTrace();
        }
    }

    /**
     * This method prints out the list of values provided, with each value separated
     * from the next one by a single whitespace
     *
     * @param entry  One of the time fields in the cron expression
     * @param values Expanded time values for the given time field
     */
    public void format(String entry, List<String> values) {
        System.out.print(String.format("%14s", entry) + " ");
        values.stream().map(x -> x.toString() + " ").forEachOrdered(System.out::print);
        System.out.println("");
    }

    /**
     * This is the universal method that parses the cron expression for
     * all fields ( minute, hour, day of month, month, day of week, command) and
     * returns a list of expanded values for each
     *
     * @param min   For a given time field, the minimum valid value. 0 for command field
     * @param max   For a given time field, the maximum valid value. 0 for command field
     * @param regex cron sub-expression for the given time field
     * @return List of expanded times for the given time field
     * @throws InvalidInputException on invalid inputs
     * @see InvalidInputException
     */
    public List<String> process(int min, int max, String regex) throws InvalidInputException {
        List<String> values = new ArrayList();
        try {
            if (regex.contains("-")) {
                String[] limits = regex.split("-");
                min = Integer.parseInt(limits[0]);
                max = Integer.parseInt(limits[1]);
            }

            if (min == 0 && max == 0) {
                values.add(regex);
            } else if (regex.contains("*/")) {
                String[] tokens = regex.split("\\/");
                for (int i = min; i <= max; i++) {
                    if (i % Integer.parseInt(tokens[1]) == 0) {
                        values.add(Integer.toString(i));
                    }
                }
            } else if (regex.contains(",")) {
                String[] tokens = regex.split("\\,");
                values = Arrays.stream(tokens).toList();
            } else if (regex.contains("*")) {
                for (int i = min; i <= max; i++) {
                    values.add(Integer.toString(i));
                }
            } else if (regex.contains("-")) {
                for (int i = min; i <= max; i++) {
                    values.add(Integer.toString(i));
                }
            } else {
                values.add(regex);
            }
        } catch (NumberFormatException numberFormatException) {
            throw new InvalidInputException("Cron expression is invalid.");
        }


        return values;
    }
}
