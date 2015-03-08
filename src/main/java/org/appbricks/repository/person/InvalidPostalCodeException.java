package org.appbricks.repository.person;

/**
 * Exception thrown when Postal Cpde validation fails.
 */
public class InvalidPostalCodeException
    extends Exception {
    
    public InvalidPostalCodeException(String message) {
        super(message);
    }
}
