package com.example.concorrente_lab10.exceptions;

public class ProgramException extends RuntimeException {

    public ProgramException() {
        super("Ocorreu um erro.");
    }
    public ProgramException(String message) {
        super(message);
    }
}
