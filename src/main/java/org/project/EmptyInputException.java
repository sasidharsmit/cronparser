package org.project;

/**
 * This exception can be thrown when the cron expression is empty or null
 */
public class EmptyInputException extends Exception {
    /**
     * Initializes the exception
     *
     * @param message Custom message can be passed to add more info to the exception
     */
    public EmptyInputException(String message) {
        super(message);
    }
}
