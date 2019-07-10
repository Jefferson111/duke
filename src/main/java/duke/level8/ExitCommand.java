package duke.level8;

public class ExitCommand extends Command {
    
    
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
