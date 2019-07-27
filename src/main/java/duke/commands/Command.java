package duke.commands;

import duke.ui.Ui;
import duke.storage.Storage;
import duke.data.taskList.TaskListAllTypes;
import duke.commons.DukeException;

public abstract class Command {
    
    public boolean isExit(){
        return false;
    }
    
    abstract public void execute(TaskListAllTypes tasks, Ui ui, Storage storage) throws DukeException;
}
