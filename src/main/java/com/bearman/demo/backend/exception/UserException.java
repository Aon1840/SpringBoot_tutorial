package com.bearman.demo.backend.exception;

public class UserException extends BaseException {

    public UserException(String code) {
        super("user: " + code);
    }

    // user register email null
    public static UserException requestNull() {
        return new UserException("register.null");
    }

    public static UserException emailNull() {
        return new UserException("register.email.null");
    }
}
