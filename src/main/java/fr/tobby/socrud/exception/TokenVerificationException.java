package fr.tobby.socrud.exception;

public class TokenVerificationException extends RuntimeException{
    public TokenVerificationException() {
    }

    public TokenVerificationException(String message) {
        super(message);
    }
}
