package commands;

import commons.DukeException;
import commons.Message;
import storage.Storage;
import tasks.Task;
import ui.Ui;

/**
 * Class representing a command to delete a task.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Creates a new DeleteCommand with the given index.
     *
     * @param index The index of the task.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes this command on the given task list and user interface.
     *
     * @param ui The user interface displaying events on the task list.
     * @param storage The storage object containing task list.
     */
    @Override
    public void execute(Ui ui, Storage storage) throws DukeException {
        try {
            Task task = storage.getTasks().remove(index);
            ui.showDelete(task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(Message.OUT_OF_BOUNDS);
        }
        storage.write();
    }
}
