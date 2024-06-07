package dev.senzalla.metakyasshuapi.settings.exception;

public class DuplicateException extends RuntimeException {
    public DuplicateException(String message) {
        super(message);
    }

    public DuplicateException(String message, String cause) {
        super(message, new Throwable(cause));
    }
}