package duke.commands;

import duke.ui.Ui;
import duke.storage.Storage;
import duke.data.TaskList;
import duke.commons.DukeException;

public class ExitCommand extends Command {
    
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
