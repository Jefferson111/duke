package parsers;

import commons.DukeException;
import commons.Message;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

public class ParserStorage {
    /**
     * Parses a task from String format back to task.
     *
     * @param line The String description of a task.
     * @return The corresponding task object.
     */
    public static Task createTaskFromStorage(String line) throws DukeException {
        String[] taskParts = line.split("\\|");
        try {
            String type = taskParts[0].strip();
            String status = taskParts[1].strip();
            String description = taskParts[2].strip();
            Task task;
            if (type.equals("D")) {
                task = new Deadline(description, taskParts[3].strip());
            } else if (type.equals("E")) {
                task = new Event(description, taskParts[3].strip());
            } else {
                task = new Todo(description);
            }
            task.setDone(status.equals("true"));
            return task;
        } catch (Exception e) {
            throw new DukeException(Message.CORRUPTED_TASK);
        }
    }

    /**
     * Parses a task from task to String format.
     *
     * @param task The task.
     * @return The corresponding String format of the task object.
     */
    public static String toStorageString(Task task) throws DukeException {
        if (task instanceof Deadline) {
            return "D | " + task.isDone() + " | " + task.getDescription() + " | " + ((Deadline) task).getDeadline();
        } else if (task instanceof Todo) {
            return  "T | " + task.isDone() + " | " + task.getDescription();
        } else if (task instanceof Event) {
            return "E | " + task.isDone() + " | " + task.getDescription() + " | " + ((Event) task).getEvent();
        }
        throw new DukeException(Message.CORRUPTED_TASK);
    }
}
