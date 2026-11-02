package taskmanager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/** Collection-backed implementation of TaskRepository. */
public class InMemoryTaskRepository implements TaskRepository {
    private final List<Task> tasks = new ArrayList<>();

    @Override
    public synchronized void add(Task task) {
        if (findById(task.getId()).isPresent()) {
            throw new IllegalArgumentException("A task with this ID already exists.");
        }
        tasks.add(task);
    }

    @Override
    public synchronized List<Task> getAll() {
        return Collections.unmodifiableList(new ArrayList<>(tasks));
    }

    @Override
    public synchronized Optional<Task> findById(int id) {
        return tasks.stream().filter(task -> task.getId() == id).findFirst();
    }

    @Override
    public synchronized boolean remove(int id) {
        return tasks.removeIf(task -> task.getId() == id);
    }
}
