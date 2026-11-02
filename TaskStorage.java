package taskmanager;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

/** Java NIO utility for saving task records to a UTF-8 text file. */
public class TaskStorage {
    private final Path file;

    public TaskStorage(Path file) {
        this.file = file;
    }

    public void save(List<Task> tasks) throws IOException {
        Path parent = file.getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }
        List<String> lines = tasks.stream().map(Task::toFileRecord).toList();
        Files.write(file, lines, StandardCharsets.UTF_8,
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public List<String> loadLines() throws IOException {
        if (Files.notExists(file)) {
            return List.of();
        }
        return Files.readAllLines(file, StandardCharsets.UTF_8);
    }

    public Path getFile() { return file; }
}
