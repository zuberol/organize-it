package com.zuber.organizeit.services.exporters.entityImporter.exceptions;

public class LoadingFileFailed extends ParserRuntimeException {

    public LoadingFileFailed() {
    }

    public LoadingFileFailed(String s) {
        super(s);
    }

    public LoadingFileFailed(String message, Throwable cause) {
        super(message, cause);
    }

    public LoadingFileFailed(Throwable cause) {
        super(cause);
    }
}
