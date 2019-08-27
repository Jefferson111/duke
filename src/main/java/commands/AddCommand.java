package commands;

import storage.Storage;
import tasks.Task;
import ui.Ui;

/**
 * Class representing a command to add a new task.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Creates a new AddCommand with the given task.
     *
     * @param task The task to add.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes this command on the given task list and user interface.
     *
     * @param ui The user interface displaying events on the task list.
     * @param storage The storage object containing task list.
     */
    @Override
    public void execute(Ui ui, Storage storage) {
        storage.getTasks().add(task);
        ui.showAdd(task);
        storage.write();
    }
}
