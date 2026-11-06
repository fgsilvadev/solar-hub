package com.fgsilva.solarhub.auth;

public record LoginResponse(String accessToken, String tokenType) {

    public static LoginResponse bearer (String accessToken) {
        return new LoginResponse(accessToken, "Bearer");
    }
}
