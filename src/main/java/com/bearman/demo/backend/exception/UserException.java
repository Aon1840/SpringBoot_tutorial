package com.bearman.demo.backend.exception;

public class UserException extends BaseException {

    public UserException(String code) {
        super("user: " + code);
    }

    // user register email null
    public static UserException requestNull() {
        return new UserException("register.null");
    }

    public static UserException userNotFound() {
        return new UserException("user.fail.notFound");
    }

    public static UserException userNotAuthorize() {
        return new UserException("user.fail.unAuthorize");
    }

    public static UserException emailNull() {
        return new UserException("register.email.null");
    }

    // CREATE
    public static UserException createEmailNull() {
        return new UserException("create.email.null");
    }

    public static UserException createEmailDuplicated() {
        return new UserException("create.email.duplicated");
    }

    public static UserException createPasswordNull() {
        return new UserException("create.password.null");
    }

    public static UserException createNameNull() {
        return new UserException("create.name.null");
    }

    public static UserException loginFailEmailNotFound() {
        return new UserException("login.fail");
    }

    public static UserException loginFailPasswordIncorrect() {
        return new UserException("login.fail");
    }
}
