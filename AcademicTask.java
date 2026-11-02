package taskmanager;

/** A task related to coursework, labs, or examinations. */
public class AcademicTask extends Task {
    private final String subject;
    private final String dueDate;

    public AcademicTask(int id, String title, String description, String subject, String dueDate) {
        super(id, title, description);
        if (subject == null || subject.isBlank()) {
            throw new IllegalArgumentException("Subject cannot be empty.");
        }
        this.subject = subject.trim();
        this.dueDate = dueDate == null || dueDate.isBlank() ? "Not set" : dueDate.trim();
    }

    public String getSubject() { return subject; }
    public String getDueDate() { return dueDate; }

    @Override
    public String getCategory() { return "Academic"; }

    @Override
    public String getTaskDetails() {
        return "Subject: " + subject + " | Due date: " + dueDate;
    }

    @Override
    public String toFileRecord() {
        return super.toFileRecord() + "|" + subject.replace("|", "/") + "|" + dueDate.replace("|", "/");
    }
}
