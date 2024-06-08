package dev.senzalla.metakyasshuapi.settings.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message, String cause) {
        super(message, new Throwable(cause));
    }
}