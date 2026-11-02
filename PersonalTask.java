package taskmanager;

/** A task for personal goals and everyday activities. */
public class PersonalTask extends Task {
    private final String priority;

    public PersonalTask(int id, String title, String description, String priority) {
        super(id, title, description);
        this.priority = (priority == null || priority.isBlank()) ? "Normal" : priority.trim();
    }

    public String getPriority() { return priority; }

    @Override
    public String getCategory() { return "Personal"; }

    @Override
    public String getTaskDetails() {
        return "Priority: " + priority;
    }
}
