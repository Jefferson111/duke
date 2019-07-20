package duke.commands;

import duke.ui.Ui;
import duke.storage.Storage;
import duke.data.TaskList;
import duke.commons.DukeException;

public abstract class Command {
    
    public boolean isExit(){
        return false;
    }
    
    abstract public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
