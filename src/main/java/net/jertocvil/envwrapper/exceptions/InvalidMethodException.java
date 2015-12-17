package net.jertocvil.envwrapper.exceptions;

import java.util.HashMap;

public class InvalidMethodException extends RuntimeException {

    public enum Reason {NOT_ANNOTATED}

    private static HashMap<Reason, String> ERROR_MESSAGES;

    static {
        ERROR_MESSAGES = new HashMap<Reason, String>();
        ERROR_MESSAGES.put(Reason.NOT_ANNOTATED, "Method doesn't have the minimum required annotations");

    }

    private Reason reason;

    public InvalidMethodException(Reason reason) {
        super(ERROR_MESSAGES.get(reason));
        this.reason = reason;
    }
}
