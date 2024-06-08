package dev.senzalla.metakyasshuapi.model.authenticate;

import lombok.Getter;

@Getter
public class Token {
    private final String HEADER;
    private final String token;

    public Token(String token) {
        this.HEADER = "Bearer";
        this.token = token;
    }
}
