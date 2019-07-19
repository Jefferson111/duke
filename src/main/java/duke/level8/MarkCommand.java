package duke.level8;

public class MarkCommand extends Command {
    
    int index;
    
    public MarkCommand(int index){
        this.index = index - 1;
    }
    
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = tasks.markAsDone(index);
        ui.showMarkAsDone(t);
        storage.save(tasks.list());
    }
}
