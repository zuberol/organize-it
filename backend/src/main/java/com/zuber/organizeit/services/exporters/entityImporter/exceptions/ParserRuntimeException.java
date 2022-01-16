package com.zuber.organizeit.services.exporters.entityImporter.exceptions;

public abstract class ParserRuntimeException extends IllegalStateException {

    public ParserRuntimeException() {
    }

    public ParserRuntimeException(String s) {
        super(s);
    }

    public ParserRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParserRuntimeException(Throwable cause) {
        super(cause);
    }
}
