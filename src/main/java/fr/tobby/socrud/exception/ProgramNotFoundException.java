package fr.tobby.socrud.exception;

public class ProgramNotFoundException extends EntityNotFoundException {
    public ProgramNotFoundException() {
    }

    public ProgramNotFoundException(String message) {
        super(message);
    }
}
