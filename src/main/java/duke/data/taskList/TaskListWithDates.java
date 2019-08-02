package duke.data.taskList;

import duke.commons.DukeException;
import duke.data.task.TaskWithDates;

import java.util.ArrayList;
import java.util.List;

public class TaskListWithDates implements TaskList<TaskWithDates> {
    private List<TaskWithDates> tasks ;

    public TaskListWithDates(List<TaskWithDates> tasks) {
        this.tasks = tasks;
    }

    public TaskListWithDates() {
        tasks = new ArrayList<>();
    }

    public void addTask(TaskWithDates task) throws DukeException {
        if (task.getDate() != null) {
            tasks.add(task);
        }
    }

    public int size() {
        return tasks.size();
    }

    public List<String> getTaskListDescriptions() {
        List<String> descriptions = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            descriptions.add( (i + 1) + "." + tasks.get(i));
        }
        return descriptions;
    }

    public TaskWithDates snoozeTask(int index, int day) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("No such task exist!");
        }
        TaskWithDates t = tasks.get(index);
        t.snooze(day);
        return t;
    }

    public void sort() {
        tasks.sort(new CustomComparator());
    }
}
