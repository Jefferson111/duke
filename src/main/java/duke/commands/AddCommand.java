package duke.commands;

import duke.ui.Ui;
import duke.storage.Storage;
import duke.data.*;
import duke.commons.DukeException;

public class AddCommand extends Command {
    
    Task task;
    
    public AddCommand(Task task){
        this.task = task;
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(task);
        ui.showAddition(task);
        ui.showListSize(tasks.size());
        storage.save(tasks.list());
    }
}
