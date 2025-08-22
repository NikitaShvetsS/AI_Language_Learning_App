package org.example.exception;

public class VocabBlockNotFoundException extends RuntimeException {
    public VocabBlockNotFoundException(String message) {
        super(message);
    }
}
