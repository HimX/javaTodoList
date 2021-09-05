public class TodoItem {

    public static String DONE_MESSAGE = "Terminada";
    public static String PENDING_MESSAGE = "Pendiente";

    private String description;
    private TodoItem next = null;
    private TodoItem previous = null;
    private boolean done = false;

    public TodoItem(String word) {
        this.description = word;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNext(TodoItem next) {
        this.next = next;
    }

    public void setPrevious(TodoItem previous) {
        this.previous = previous;
    }

    public TodoItem getNext() {
        return next;
    }

    public TodoItem getPrevious() {
        return previous;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public boolean getDone() {
        return this.done;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
