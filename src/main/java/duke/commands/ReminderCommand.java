package duke.commands;

import duke.commons.DukeException;
import duke.data.task.Task;
import duke.data.task.TaskWithDates;
import duke.data.taskList.TaskListAllTypes;
import duke.data.taskList.TaskListWithDates;
import duke.storage.Storage;
import duke.ui.Ui;

public class ReminderCommand extends Command {

    TaskListWithDates tasksWithDates;
    TaskListAllTypes tasksWithoutDates;

    public ReminderCommand() {
        tasksWithDates = new TaskListWithDates();
        tasksWithoutDates = new TaskListAllTypes();
    }

    @Override
    public void execute(TaskListAllTypes tasks, Ui ui, Storage storage) throws DukeException {
        getTasksWithDates(tasks);
        tasksWithDates.sort();
        ui.showTaskList(tasksWithDates.getTaskListDescriptions());
        ui.showTaskList(tasksWithoutDates.getTaskListDescriptions());
    }

    private void getTasksWithDates(TaskListAllTypes tasks) throws DukeException {
        for (int i = 0; i < tasks.size(); ++i) {
            Task t = tasks.getTask(i);
            if (t instanceof TaskWithDates && ((TaskWithDates) t).getDate() != null) {
                tasksWithDates.addTask((TaskWithDates) t);
            } else {
                tasksWithoutDates.addTask(t);
            }
        }
    }
}
