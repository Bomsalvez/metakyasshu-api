package dev.senzalla.metakyasshuapi.settings.exception;

public class ParticipationException extends RuntimeException {

    public ParticipationException(String message, String cause) {
        super(message, new Throwable(cause));
    }
}