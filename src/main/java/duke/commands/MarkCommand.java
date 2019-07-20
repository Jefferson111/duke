package duke.commands;

import duke.ui.Ui;
import duke.storage.Storage;
import duke.data.*;
import duke.commons.DukeException;

public class MarkCommand extends Command {
    
    int index;
    
    public MarkCommand(int index){
        this.index = index - 1;
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = tasks.markAsDone(index);
        ui.showMarkAsDone(t);
        storage.save(tasks.list());
    }
}
