package com.fgsilva.solarhub.user;

public class EmailAlreadyInUseException extends RuntimeException {

    public EmailAlreadyInUseException() {
        super("Email already in use");
    }
}
