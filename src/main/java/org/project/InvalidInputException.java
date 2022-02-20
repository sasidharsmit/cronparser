package org.project;

/**
 * This exception can be thrown when the cron expression is invalid
 */
public class InvalidInputException extends Exception {
    /**
     * Initializes the exception
     *
     * @param message Custom message can be passed to add more info to the exception
     */
    public InvalidInputException(String message) {
        super(message);
    }
}
