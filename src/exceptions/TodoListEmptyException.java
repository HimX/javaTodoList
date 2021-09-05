package exceptions;

public class TodoListEmptyException extends RuntimeException {
    public TodoListEmptyException(String message) {
        super(message);
    }
}
