package com.zuber.organizeit.services.exporters.entityImporter.exceptions;

public class UnexpectedClassException extends ParserRuntimeException {
    public UnexpectedClassException() {
    }

    public UnexpectedClassException(String s) {
        super(s);
    }

    public UnexpectedClassException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnexpectedClassException(Throwable cause) {
        super(cause);
    }
}
