package dev.senzalla.metakyasshuapi.settings.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorDto {

    private String property;
    private final String error;
    private final HttpStatus code;

    public ErrorDto(String error, HttpStatus code) {
        this.error = error;
        this.code = code;
    }

    public ErrorDto(String error, String property, HttpStatus code) {
        this.property = property;
        this.error = error;
        this.code = code;
    }

    @Override
    public String toString() {
        return """
                {
                    "error": "%s",
                    "code": "%s"
                }
                """.formatted(error, code);
    }
}