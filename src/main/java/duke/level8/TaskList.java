package duke.level8;



import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> tasks ;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) throws DukeException {
        tasks.add(task);
    }

    public Task markAsDone(int index) {
        Task t = tasks.get(index - 1);
        t.setDone(true);
        return t;
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

    public List<Task> list() {
        return tasks;
    }

    public Task delete(int index) {
        return tasks.remove(index);
    }
}
