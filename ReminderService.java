package taskmanager;

import java.util.List;

/** Runnable background worker: demonstrates multithreading. */
public class ReminderService implements Runnable {
    private final TaskRepository repository;

    public ReminderService(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run() {
        List<Task> pending = repository.getAll().stream()
                .filter(task -> !task.isCompleted()).toList();
        System.out.println("\\n[Reminder thread] You have " + pending.size() + " pending task(s).");
        for (Task task : pending) {
            System.out.println("[Reminder thread] " + task.getTitle());
        }
        System.out.println("[Reminder thread] Reminder check finished.\\n");
    }
}
