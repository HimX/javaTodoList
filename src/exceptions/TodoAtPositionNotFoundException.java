package exceptions;

public class TodoAtPositionNotFoundException extends RuntimeException {
    public TodoAtPositionNotFoundException(String message) {
        super(message);
    }
}
