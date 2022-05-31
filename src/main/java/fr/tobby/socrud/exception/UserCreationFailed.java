package fr.tobby.socrud.exception;

public class UserCreationFailed extends RuntimeException {
    public UserCreationFailed(String message) {
        super(message);
    }
}
