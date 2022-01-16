package com.zuber.organizeit.services.exporters.entityImporter.exceptions;

public class ParserIllegalState extends IllegalStateException {

    public static String CANT_MERGE = "Can't merge with this tag.";
    public static String TAG_NOT_KNOWN = "tag not known";


    public ParserIllegalState() {
    }

    public ParserIllegalState(String s) {
        super(s);
    }

    public ParserIllegalState(String message, Throwable cause) {
        super(message, cause);
    }

    public ParserIllegalState(Throwable cause) {
        super(cause);
    }
}
