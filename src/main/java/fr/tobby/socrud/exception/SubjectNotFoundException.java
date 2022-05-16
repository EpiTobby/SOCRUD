package fr.tobby.socrud.exception;

public class SubjectNotFoundException extends RuntimeException{
    public SubjectNotFoundException() {
    }

    public SubjectNotFoundException(String message) {
        super(message);
    }
}
