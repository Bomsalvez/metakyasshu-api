package dev.senzalla.metakyasshuapi.settings.exception;

public class ImageException extends RuntimeException {

    public ImageException(String message) {
        super(message);
    }

    public ImageException(String message, String cause) {
        super(message,  new Throwable(cause));
    }
}