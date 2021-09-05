import java.util.Scanner;

import exceptions.TodoAtPositionNotFoundException;
import exceptions.TodoListEmptyException;

public class App {

    private static String PRINT_LIST_COMMAND = "print";
    private static String NEW_TASK_COMMAND = "new";
    private static String EDIT_TASK_COMMAND = "edit";
    private static String REMOVE_TASK_COMMAND = "remove";
    private static String EXIT_COMMAND = "exit";
    private static String HELP_COMMAND = "help";
    private static String SET_DONE_COMMAND = "set_done";
    private static String SET_UNDONE_COMMAND = "set_undone";
    private static String GET_DONE_COMMAND = "get_done";
    private static String GET_UNDONE_COMMAND = "get_undone";

    public static void main(String[] args) throws Exception {
        printMenu();
        TodoList list = new TodoList();
        Scanner scanner = new Scanner(System.in).useDelimiter("\\n");
        while (true) {
            scanner.useDelimiter("\\n");
            System.out.print("> ");
            String command = scanner.next();
            command = command.toLowerCase();

            if (App.EXIT_COMMAND.equals(command)) {
                System.out.println("Hasta la próxima :)");
                break;
            } else if (App.PRINT_LIST_COMMAND.equals(command)) {
                App.printList(list);
            } else if (App.NEW_TASK_COMMAND.equals(command)) {
                App.newTask(list, scanner);
            } else if (App.EDIT_TASK_COMMAND.equals(command)) {
                App.editTask(list, scanner);
            } else if (App.REMOVE_TASK_COMMAND.equals(command)) {
                App.removeTask(list, scanner);
            } else if (App.HELP_COMMAND.equals(command)) {
                App.printCommands();
            } else if (App.SET_DONE_COMMAND.equals(command)) {
                App.setDone(list, scanner);
            } else if (App.SET_UNDONE_COMMAND.equals(command)) {
                App.setUndone(list, scanner);
            } else if (App.GET_DONE_COMMAND.equals(command)) {
                App.getDone(list);
            } else if (App.GET_UNDONE_COMMAND.equals(command)) {
                App.getUndone(list);
            } else {
                System.out.println("No se encuentra el comando: " + command);
                App.printCommands();
            }
        }
        scanner.close();
    }

    public static void printMenu() {
        System.out.println("=====================================");
        System.out.println("    Bienvenido a ***TO-DO List***    ");
        System.out.println("=====================================");
        App.printCommands();
    }

    public static void printCommands() {
        System.out.println("Comandos disponibles:");
        System.out.println("+ " + App.PRINT_LIST_COMMAND + " - Imprimir la lista de tareas.");
        System.out.println("+ " + App.NEW_TASK_COMMAND + " - Crear una nueva tarea.");
        System.out.println("+ " + App.EDIT_TASK_COMMAND + " - Editar una tarea.");
        System.out.println("+ " + App.REMOVE_TASK_COMMAND + " - Eliminar una tarea.");
        System.out.println("+ " + App.SET_DONE_COMMAND + " - Marcar una tarea como terminada.");
        System.out.println("+ " + App.SET_UNDONE_COMMAND + " - Marcar una tarea como pendiente.");
        System.out.println("+ " + App.GET_DONE_COMMAND + " - Obtener el listado de las tareas terminadas.");
        System.out.println("+ " + App.GET_UNDONE_COMMAND + " - Obtener el listado de la tareas pendientes.");
        System.out.println("+ " + App.EXIT_COMMAND + " - Salir de TO-DO List.");
        System.out.println("+ " + App.HELP_COMMAND + " - Ver la lista de comandos.");
    }

    public static void printList(TodoList list) {
        System.out.println("=====================================");
        System.out.println("         TO-DO List, tareas.         ");
        System.out.println("=====================================");
        if (list.getSize() == 0) {
            System.out.println("La lista se encuentra vacía.");
            return;
        }
        list.print();
    }

    public static void newTask(TodoList list, Scanner scanner) {
        System.out.print("Nombre de la tarea: ");
        String task = scanner.next();

        if (task.isEmpty()) {
            System.out.println("El nombre de la tarea está vacío, no se ha creado la tarea.");
            return;
        }

        TodoItem item = new TodoItem(task);
        list.add(item);
        System.out.println("Se ha creado una nueva tarea.");
    }

    public static void editTask(TodoList list, Scanner scanner) {
        System.out.print("¿Qué tarea deseas editar? Indice: ");
        int index = scanner.nextInt();
        TodoItem item = null;

        try {
            item = list.at(index);
        } catch (TodoListEmptyException e) {
            System.out.println("La lista se encuentra vacía, puedes crear una tarea con el comando \"new\"");
            return;
        } catch (TodoAtPositionNotFoundException e) {
            System.out.println("La tarea seleccionada no existe.");
            return;
        }

        System.out.print("Nueva descripción de la tarea: ");
        String description = scanner.next();
        item.setDescription(description);
        System.out.println("Se ha actualizado la tarea " + index);
    }

    public static void removeTask(TodoList list, Scanner scanner) {
        System.out.print("¿Qué tarea deseas eliminar? Indice: ");
        int index = scanner.nextInt();

        try {
            list.remove(index);
        } catch (TodoListEmptyException e) {
            System.out.println("La lista se encuentra vacía, puedes crear una tarea con el comando \"new\"");
            return;
        } catch (TodoAtPositionNotFoundException e) {
            System.out.println("La tarea seleccionada no existe.");
            return;
        }

        System.out.println("Se ha eliminado la tarea " + index);
    }

    public static void setDone(TodoList list, Scanner scanner) {
        System.out.print("¿Qué tarea desea marcar como terminada? Indice: ");
        int index = scanner.nextInt();

        try {
            list.setDone(index);
        } catch (TodoListEmptyException e) {
            System.out.println("La lista se encuentra vacía, puedes crear una tarea con el comando \"new\"");
            return;
        } catch (TodoAtPositionNotFoundException e) {
            System.out.println("La tarea seleccionada no existe.");
            return;
        }

        System.out.println("Se ha marcado como terminada la tarea " + index);
    }

    public static void setUndone(TodoList list, Scanner scanner) {
        System.out.print("¿Qué tarea desea marcar como pendiente? Indice: ");
        int index = scanner.nextInt();

        try {
            list.setUndone(index);
        } catch (TodoListEmptyException e) {
            System.out.println("La lista se encuentra vacía, puedes crear una tarea con el comando \"new\"");
            return;
        } catch (TodoAtPositionNotFoundException e) {
            System.out.println("La tarea seleccionada no existe.");
            return;
        }

        System.out.println("Se ha marcado como pendiente la tarea " + index);
    }

    public static void getDone(TodoList list) {
        System.out.println("=====================================");
        System.out.println("        TO-DO List, terminadas.      ");
        System.out.println("=====================================");
        TodoList doneList = list.getDone();
        doneList.print();
    }

    public static void getUndone(TodoList list) {
        System.out.println("=====================================");
        System.out.println("       TO-DO List, pendientes.       ");
        System.out.println("=====================================");
        TodoList undoneList = list.getUndone();
        undoneList.print();
    }
}
