import exceptions.TodoAtPositionNotFoundException;
import exceptions.TodoListEmptyException;

public class TodoList {
    private TodoItem firstNode = null;
    private TodoItem lastNode = null;
    private int size;

    /**
     * Obtener el tamaño de la lista
     * 
     * @return
     */
    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Agregar un elemento al final de la lista
     * 
     * @param node
     */
    public void add(TodoItem node) {
        if (this.firstNode == null) {
            this.firstNode = node;
        }

        if (this.lastNode != null) {
            this.lastNode.setNext(node);
            node.setPrevious(this.lastNode);
        }

        this.lastNode = node;
        ++this.size;
    }

    /**
     * Obtener un elemento de la lista por su indice
     * 
     * @param index
     * @return
     * @throws TodoListEmptyException
     * @throws TodoAtPositionNotFoundException
     */
    public TodoItem at(int index) {
        if (this.firstNode == null) {
            throw new TodoListEmptyException("The list empty.");
        }

        if (!this.exists(index)) {
            throw new TodoAtPositionNotFoundException("The word at the position " + index + " does not exists.");
        }

        TodoItem actualNode = this.firstNode;
        int position = 0;

        while (actualNode != null) {
            if (position == index) {
                break;
            }

            ++position;
            actualNode = actualNode.getNext();
        }

        return actualNode;
    }

    /**
     * Quitar un elemento de la lista por un indice
     * 
     * @param index
     */
    public void remove(int index) {
        TodoItem actualNode = this.at(index);
        TodoItem previous = actualNode.getPrevious();
        TodoItem next = actualNode.getNext();

        if (previous != null) {
            previous.setNext(next);
        }

        if (next != null) {
            next.setPrevious(previous);
        }

        this.size--;
    }

    /**
     * Concatenar la lista actual con otra lista
     * 
     * @param list
     */
    public void concat(TodoList list) {
        int externalListSize = list.getSize();
        for (int i = 0; i < externalListSize; ++i) {
            this.add(list.at(i));
        }
    }

    public void replace(int index, TodoItem element) {
        TodoItem actualNode = this.at(index);
        TodoItem previous = actualNode.getPrevious();
        TodoItem next = actualNode.getNext();

        if (previous != null) {
            previous.setNext(element);
            element.setPrevious(previous);
        }

        if (next != null) {
            next.setPrevious(element);
            element.setNext(next);
        }
    }

    /**
     * Imprimir la lista
     */
    public void print() {
        int position = 0;
        TodoItem actualNode = this.firstNode;
        while (actualNode != null) {
            String status = TodoItem.PENDING_MESSAGE;
            if (actualNode.getDone()) {
                status = TodoItem.DONE_MESSAGE;
            }

            System.out.println(position + ". " + actualNode.getDescription() + " - " + status);
            actualNode = actualNode.getNext();
            ++position;
        }
    }

    /**
     * Comprobar si un elemento está en la lista, por indice
     * 
     * @param index
     * @return
     */
    public boolean exists(int index) {
        if (index > (this.size - 1)) {
            return false;
        }

        return true;
    }

    /**
     * Comprobar si un elemento de la lista contiene una palabra en concreto
     * 
     * @param word
     * @return
     */
    public boolean exists(String word) {
        TodoItem actualNode = this.firstNode;
        while (actualNode != null) {
            if (actualNode.getDescription() == word) {
                return true;
            }
            actualNode = actualNode.getNext();
        }
        return false;
    }

    public void setDone(int index) {
        TodoItem item = this.at(index);
        item.setDone(true);
    }

    public void setUndone(int index) {
        TodoItem item = this.at(index);
        item.setDone(false);
    }

    public TodoList getDone() {
        TodoList doneList = new TodoList();
        TodoItem currentItem = this.firstNode;

        while (currentItem != null) {
            if (currentItem.getDone()) {
                doneList.add(currentItem);
            }
            currentItem = currentItem.getNext();
        }

        return doneList;
    }

    public TodoList getUndone() {
        TodoList undoneList = new TodoList();
        TodoItem currentItem = this.firstNode;

        while (currentItem != null) {
            if (!currentItem.getDone()) {
                undoneList.add(currentItem);
            }
            currentItem = currentItem.getNext();
        }

        return undoneList;
    }
}
