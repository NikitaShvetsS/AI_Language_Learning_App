package org.example.exception;

public class TextBlockNotFoundException extends RuntimeException {

    public TextBlockNotFoundException(String message) {
        super(message);
    }
}
