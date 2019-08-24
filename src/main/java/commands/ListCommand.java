package commands;

import tasks.Task;
import ui.Ui;

import java.util.ArrayList;

/**
 * Class representing a command to list items in a task list.
 */
public class ListCommand extends Command {
    /**
     * Executes this command on the given task list and user interface.
     *
     * @param ui The user interface displaying events on the task list.
     * @param tasks The task list.
     */
    @Override
    public void execute(Ui ui, ArrayList<Task> tasks) {
        ui.showList(tasks);
    }
}
