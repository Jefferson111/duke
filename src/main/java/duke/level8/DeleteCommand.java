package duke.level8;

public class DeleteCommand extends Command {
    
    int index;
    
    public DeleteCommand(int index){
        this.index = index - 1;
    }
    
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = tasks.delete(index);
        ui.showDeletion(t);
        storage.save(tasks.list());
    }
}
