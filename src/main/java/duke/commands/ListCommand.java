package duke.commands;

import duke.ui.Ui;
import duke.storage.Storage;
import duke.data.TaskList;
import duke.commons.DukeException;

public class ListCommand extends Command {
    
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showTaskList(tasks.getTaskListDescriptions());
    }

}
