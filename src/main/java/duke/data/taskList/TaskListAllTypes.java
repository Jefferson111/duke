package duke.data.taskList;

import duke.commons.DukeException;
import duke.data.task.Task;
import duke.data.task.TaskWithDates;

import java.util.ArrayList;
import java.util.List;

public class TaskListAllTypes implements TaskList<Task> {

    private List<Task> tasks ;

    public TaskListAllTypes(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskListAllTypes() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) throws DukeException {
        tasks.add(task);
    }

    public Task markAsDone(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("No such task exist!");
        }
        Task t = tasks.get(index);
        t.setDone(true);
        return t;
    }

    public int size() {
        return tasks.size();
    }

    public Task getTask(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("No such task exist!");
        }
        Task t = tasks.get(index);
        return t;
    }


    public List<String> getTaskListDescriptions() {
        List<String> descriptions = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            descriptions.add( (i + 1) + "." + tasks.get(i));
        }
        return descriptions;
    }

    public List<Task> list() {
        return tasks;
    }

    public Task delete(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("No such task exist!");
        }
        return tasks.remove(index);
    }

    public Task snoozeTask(int index, int day) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("No such task exist!");
        }
        Task t = tasks.get(index);
        if (t instanceof TaskWithDates) {
            ((TaskWithDates) t).snooze(day);
            return t;
        } else {
            throw new DukeException("This type of task cannot be snoozed!");
        }
    }
}