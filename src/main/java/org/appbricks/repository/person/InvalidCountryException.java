package org.appbricks.repository.person;

/**
 * Exception thrown when Country validation fails.
 */
public class InvalidCountryException 
    extends Exception {
    
    public InvalidCountryException(String message) {
        super(message);
    }
}
