package duke.commands;

import duke.ui.Ui;
import duke.storage.Storage;
import duke.data.Task;
import duke.data.taskList.TaskListAllTypes;
import duke.commons.DukeException;

public class DeleteCommand extends Command {
    
    int index;
    
    public DeleteCommand(int index){
        this.index = index - 1;
    }
    
    @Override
    public void execute(TaskListAllTypes tasks, Ui ui, Storage storage) throws DukeException {
        Task t = tasks.delete(index);
        ui.showDeletion(t);
        storage.save(tasks.list());
    }
}
