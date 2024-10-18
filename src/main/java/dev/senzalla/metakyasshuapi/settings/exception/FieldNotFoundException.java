package dev.senzalla.metakyasshuapi.settings.exception;

public class FieldNotFoundException extends RuntimeException {
    public FieldNotFoundException(String message) {
        super(message);
    }

    public FieldNotFoundException(String message, String cause) {
        super(message, new Throwable(cause));
    }
}