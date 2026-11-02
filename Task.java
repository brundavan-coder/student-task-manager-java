package taskmanager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Base class for all tasks. Demonstrates encapsulation and inheritance.
 */
public abstract class Task {
    private final int id;
    private String title;
    private String description;
    private boolean completed;
    private final LocalDateTime createdAt;

    protected Task(int id, String title, String description) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Task title cannot be empty.");
        }
        this.id = id;
        this.title = title.trim();
        this.description = description == null ? "" : description.trim();
        this.completed = false;
        this.createdAt = LocalDateTime.now();
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public boolean isCompleted() { return completed; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Task title cannot be empty.");
        }
        this.title = title.trim();
    }

    public void markCompleted() { completed = true; }

    public abstract String getCategory();
    public abstract String getTaskDetails();

    public String toDisplayString() {
        String status = completed ? "COMPLETED" : "PENDING";
        return String.format("#%d | %-9s | %-9s | %s", id, getCategory(), status, title);
    }

    public String toFileRecord() {
        return id + "|" + getCategory() + "|" + completed + "|" +
                title.replace("|", "/") + "|" + description.replace("|", "/") +
                "|" + createdAt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}
