package duke.data.taskList;

import duke.commons.DukeException;
import java.util.List;

public interface TaskList<T> {

    public void addTask(T t) throws DukeException;
    public int size();
    public List<String> getTaskListDescriptions();
}
