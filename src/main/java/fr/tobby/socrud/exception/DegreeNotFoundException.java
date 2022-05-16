package fr.tobby.socrud.exception;

public class DegreeNotFoundException extends EntityNotFoundException {
    public DegreeNotFoundException() {
    }

    public DegreeNotFoundException(String message) {
        super(message);
    }
}
