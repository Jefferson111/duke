package duke.level8;

public abstract class Command {
    
    public boolean isExit(){
        return false;
    }
    
    abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
