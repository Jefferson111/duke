package duke.logic.commands;

import duke.ui.Ui;
import duke.storage.Storage;
import duke.data.taskList.TaskListAllTypes;
import duke.commons.DukeException;

public class ExitCommand extends Command {
    
    
    @Override
    public void execute(TaskListAllTypes tasks, Ui ui, Storage storage) throws DukeException {
        ui.showExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
