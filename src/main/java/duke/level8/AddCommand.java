package duke.level8;

public class AddCommand extends Command {
    
    Task task;
    
    public AddCommand(Task task){
        this.task = task;
    }
    
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(task);
        ui.showAddition(task);
        ui.showListSize(tasks.size());
        storage.save(tasks.list());
    }
}
