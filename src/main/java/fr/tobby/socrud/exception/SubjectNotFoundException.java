package fr.tobby.socrud.exception;

public class SubjectNotFoundException extends EntityNotFoundException {
    public SubjectNotFoundException() {
    }

    public SubjectNotFoundException(String message) {
        super(message);
    }
}
