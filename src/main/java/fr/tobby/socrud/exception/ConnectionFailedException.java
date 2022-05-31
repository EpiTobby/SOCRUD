package fr.tobby.socrud.exception;

public class ConnectionFailedException extends RuntimeException{
    public ConnectionFailedException() {
    }

    public ConnectionFailedException(String message) {
        super(message);
    }
}
