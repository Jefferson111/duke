package commands;

import tasks.Task;
import ui.Ui;

import java.util.ArrayList;

/**
 * Class representing a command to mark a task as done.
 */
public class MarkDoneCommand extends Command {
    private int index;

    /**
     * Creates a new MarkDoneCommand with the given index.
     *
     * @param index The index of the task.
     */
    public MarkDoneCommand(int index) {
        this.index = index;
    }

    /**
     * Executes this command on the given task list and user interface.
     *
     * @param ui The user interface displaying events on the task list.
     * @param tasks The task list.
     */
    @Override
    public void execute(Ui ui, ArrayList<Task> tasks) {
        Task task = tasks.get(index);
        task.setDone(true);
        ui.showMarkDone(task);
    }
}
