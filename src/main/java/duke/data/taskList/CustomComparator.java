package duke.data.taskList;

import duke.data.TaskWithDates;

import java.util.Comparator;

public class CustomComparator implements Comparator<TaskWithDates> {
    @Override
    public int compare(TaskWithDates t1, TaskWithDates t2) {
        return t1.getDate().compareTo(t2.getDate());
    }
}
