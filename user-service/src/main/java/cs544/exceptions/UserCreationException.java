package cs544.exceptions;

public class UserCreationException extends RuntimeException {
    public UserCreationException() {
        super("User cannot be created");
    }
}