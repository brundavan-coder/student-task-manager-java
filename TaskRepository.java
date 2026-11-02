package taskmanager;

import java.util.List;
import java.util.Optional;

/** Interface separates task storage operations from their implementation. */
public interface TaskRepository {
    void add(Task task);
    List<Task> getAll();
    Optional<Task> findById(int id);
    boolean remove(int id);
}
