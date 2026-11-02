package taskmanager;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Console entry point for the Student Task Manager.
 * Run with: java -cp out taskmanager.TaskManagerApp
 */
public class TaskManagerApp {
    private final TaskRepository repository = new InMemoryTaskRepository();
    private final TaskStorage storage = new TaskStorage(Path.of("data", "tasks.txt"));
    private int nextId = 1;

    public static void main(String[] args) {
        new TaskManagerApp().run();
    }

    private void run() {
        System.out.println("================================");
        System.out.println("      STUDENT TASK MANAGER");
        System.out.println("================================");
        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;
            while (running) {
                printMenu();
                System.out.print("Choose an option: ");
                String choice = scanner.nextLine().trim();
                try {
                    switch (choice) {
                        case "1" -> addAcademicTask(scanner);
                        case "2" -> addPersonalTask(scanner);
                        case "3" -> listTasks();
                        case "4" -> completeTask(scanner);
                        case "5" -> removeTask(scanner);
                        case "6" -> runReminder();
                        case "7" -> saveTasks();
                        case "8" -> showSavedFile();
                        case "0" -> running = false;
                        default -> System.out.println("Invalid option. Choose 0-8.");
                    }
                } catch (IllegalArgumentException ex) {
                    System.out.println("Input error: " + ex.getMessage());
                } catch (IOException ex) {
                    System.out.println("File error: " + ex.getMessage());
                } catch (Exception ex) {
                    System.out.println("Unexpected error: " + ex.getMessage());
                }
            }
        }
        System.out.println("Thank you for using Student Task Manager.");
    }

    private void printMenu() {
        System.out.println("\\n1. Add academic task");
        System.out.println("2. Add personal task");
        System.out.println("3. View all tasks");
        System.out.println("4. Mark task complete");
        System.out.println("5. Delete task");
        System.out.println("6. Run background reminder");
        System.out.println("7. Save tasks to file (NIO)");
        System.out.println("8. View saved file records");
        System.out.println("0. Exit");
    }

    private void addAcademicTask(Scanner scanner) {
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();
        System.out.print("Subject: ");
        String subject = scanner.nextLine();
        System.out.print("Due date (e.g. 2026-11-10): ");
        String dueDate = scanner.nextLine();
        repository.add(new AcademicTask(nextId++, title, description, subject, dueDate));
        System.out.println("Academic task added successfully.");
    }

    private void addPersonalTask(Scanner scanner) {
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();
        System.out.print("Priority (Low/Normal/High): ");
        String priority = scanner.nextLine();
        repository.add(new PersonalTask(nextId++, title, description, priority));
        System.out.println("Personal task added successfully.");
    }

    private void listTasks() {
        List<Task> tasks = repository.getAll();
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }
        System.out.println("\\n--- TASK LIST ---");
        for (Task task : tasks) {
            System.out.println(task.toDisplayString());
            System.out.println("    " + task.getTaskDetails());
        }
    }

    private void completeTask(Scanner scanner) {
        int id = readId(scanner);
        Optional<Task> task = repository.findById(id);
        if (task.isPresent()) {
            task.get().markCompleted();
            System.out.println("Task marked as completed.");
        } else {
            System.out.println("No task found with ID " + id + ".");
        }
    }

    private void removeTask(Scanner scanner) {
        int id = readId(scanner);
        System.out.println(repository.remove(id) ? "Task deleted." : "Task ID not found.");
    }

    private int readId(Scanner scanner) {
        System.out.print("Enter task ID: ");
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Task ID must be a whole number.");
        }
    }

    private void runReminder() {
        Thread reminderThread = new Thread(new ReminderService(repository), "task-reminder");
        reminderThread.start();
        try {
            reminderThread.join();
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            System.out.println("Reminder was interrupted.");
        }
    }

    private void saveTasks() throws IOException {
        storage.save(repository.getAll());
        System.out.println("Saved " + repository.getAll().size() + " task(s) to " + storage.getFile());
    }

    private void showSavedFile() throws IOException {
        List<String> lines = storage.loadLines();
        if (lines.isEmpty()) {
            System.out.println("No saved records yet. Choose option 7 first.");
        } else {
            System.out.println("--- SAVED FILE RECORDS ---");
            lines.forEach(System.out::println);
        }
    }
}
