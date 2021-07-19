package com.bearman.demo.backend.exception;

public class FileException extends BaseException {

    public FileException(String code) {
        super("File: " + code);
    }

    public static FileException fileNull() {
        return new FileException("file null");
    }

    public static FileException fileMaxSize() {
        return new FileException("file max size");
    }

    public static FileException fileUnSupport() {
        return new FileException("file unsupported");
    }
}
