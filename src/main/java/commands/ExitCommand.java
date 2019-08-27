package commands;

import storage.Storage;
import ui.Ui;

/**
 * Class representing a command to exit Duke.
 */
public class ExitCommand extends Command {
    /**
     * Executes this command on the given task list and user interface.
     *
     * @param ui The user interface displaying events on the task list.
     * @param storage The storage object containing task list.
     */
    @Override
    public void execute(Ui ui, Storage storage) {
        ui.showBye();
    }
}
