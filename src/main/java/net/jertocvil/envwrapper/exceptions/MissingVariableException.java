package net.jertocvil.envwrapper.exceptions;

public class MissingVariableException extends RuntimeException {

    public MissingVariableException(String varName) {
        super(String.format("Non optional variable '%s' undefined", varName));
    }
}
