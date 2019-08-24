package commands;

import tasks.Task;
import ui.Ui;

import java.util.ArrayList;

/**
 * Abstract class representing individual commands.
 */
public abstract class Command {
    /**
     * Executes this command on the given task list and user interface.
     *
     * @param ui The user interface displaying events on the task list.
     * @param tasks The task list.
     */
    public abstract void execute(Ui ui, ArrayList<Task> tasks);
}
