package duke.logic.commands;

import duke.commons.DukeException;
import duke.data.task.Task;
import duke.data.taskList.TaskListAllTypes;
import duke.storage.Storage;
import duke.ui.Ui;

public class SnoozeCommand extends Command {

    int index;
    int day;

    public SnoozeCommand(int index, int day){
        this.index = index - 1;
        this.day = day;
    }

    @Override
    public void execute(TaskListAllTypes tasks, Ui ui, Storage storage) throws DukeException {
        Task t = tasks.snoozeTask(index, day);
        ui.showSnooze(t);
        storage.save(tasks.list());
    }
}
