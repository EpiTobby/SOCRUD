package fr.tobby.socrud.exception;

public class ConnectionFailedException extends RuntimeException{
    public ConnectionFailedException(String message) {
        super(message);
    }
}
