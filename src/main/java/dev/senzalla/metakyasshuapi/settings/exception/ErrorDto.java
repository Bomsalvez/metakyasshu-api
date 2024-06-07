package dev.senzalla.metakyasshuapi.settings.exception;

import lombok.Getter;

@Getter
public class ErrorDto {
    private String property;
    private final String error;
    private final int code;

    public ErrorDto(String error, int code) {
        this.error = error;
        this.code = code;
    }

    public ErrorDto(String error, String property, int code) {
        this.property = property;
        this.error = error;
        this.code = code;
    }
}