package org.example.exception;

public class DailyPackNotFoundException extends RuntimeException {

    public DailyPackNotFoundException(String message) {
        super(message);
    }
}
