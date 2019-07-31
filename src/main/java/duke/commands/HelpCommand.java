package duke.commands;

import duke.ui.Ui;
import duke.storage.Storage;
import duke.data.taskList.TaskListAllTypes;
import duke.commons.DukeException;

public class HelpCommand extends Command {

    @Override
    public void execute(TaskListAllTypes tasks, Ui ui, Storage storage) throws DukeException {
        ui.showHelp();
    }
}