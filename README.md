# Student Task Manager (Java)

A console-based task management project for students. It demonstrates **inheritance, method overriding, interfaces, collections, multithreading, exception handling, and Java NIO**.

## Requirements
- JDK 17 or later (uses modern switch expressions and `Stream.toList()`)
- Terminal / Command Prompt

## Run on Windows
Open Command Prompt in this folder:

```bat
javac -d out src\taskmanager\*.java
java -cp out taskmanager.TaskManagerApp
```

## Run on macOS/Linux
```bash
javac -d out src/taskmanager/*.java
java -cp out taskmanager.TaskManagerApp
```

## Features
1. Add academic tasks with subject and due date.
2. Add personal tasks with priority.
3. List all tasks and their status.
4. Mark tasks complete or delete tasks.
5. Run a reminder check in a separate thread.
6. Save task records using Java NIO to `data/tasks.txt`.
7. Read saved records back from the file.

## Concepts demonstrated
| Java concept | Where it appears |
|---|---|
| Inheritance | `AcademicTask` and `PersonalTask` extend `Task` |
| Method overriding | Subclasses override `getCategory()` and `getTaskDetails()` |
| Interface | `TaskRepository` defines storage operations |
| Collections | `ArrayList`, `List`, `Optional`, streams |
| Multithreading | `ReminderService implements Runnable`; `Thread.start()` and `join()` |
| Exception handling | `try/catch`, `IllegalArgumentException`, `IOException`, `InterruptedException` |
| Java NIO | `Path`, `Files`, `StandardCharsets`, `StandardOpenOption` |

## Suggested demo
Add one academic task and one personal task, list them, mark one complete, run the reminder thread, save the file, and view saved records.

## Screenshots
See the `screenshots/` directory for sample terminal-style screenshots. These are illustrative sample outputs; run the project locally to capture screenshots from your own execution for submission.
