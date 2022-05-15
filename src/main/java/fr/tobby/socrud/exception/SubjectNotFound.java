package fr.tobby.socrud.exception;

public class SubjectNotFound extends RuntimeException{
    public SubjectNotFound() {
    }

    public SubjectNotFound(String message) {
        super(message);
    }
}
