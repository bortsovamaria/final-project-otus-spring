package ru.otus.spring.exceptions;

public class FailParseFileException extends RuntimeException {
    public FailParseFileException(String message) {
        super(message);
    }
}
