package dev.senzalla.metakyasshuapi.settings.exception;

import lombok.Getter;

import java.text.MessageFormat;

@Getter
public class ExcludeException extends RuntimeException {
    private final String messageCode;

    public ExcludeException(String messageCode, Object... args) {
        super(MessageFormat.format(messageCode, args));
        this.messageCode = messageCode;
    }
}