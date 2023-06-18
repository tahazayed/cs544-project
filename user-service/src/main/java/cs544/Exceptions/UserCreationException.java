package cs544.Exceptions;

public class UserCreationException extends RuntimeException {
    public UserCreationException() {
        super("User cannot be created");
    }
}