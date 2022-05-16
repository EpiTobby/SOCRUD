package fr.tobby.socrud.exception;

public class ProgramNotFoundException extends RuntimeException {
    public ProgramNotFoundException() {
    }

    public ProgramNotFoundException(String message) {
        super(message);
    }
}
