package duke.level8;

public class ListCommand extends Command {
    
    
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showTaskList(tasks.getTaskListDescriptions());
    }

}
