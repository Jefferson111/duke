package commands;

import storage.Storage;
import tasks.Task;
import ui.Ui;

import java.util.ArrayList;

/**
 * Class representing a command to find a task by keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Creates a new FindCommand with the given keyword.
     *
     * @param keyword The keyword to find.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes this command on the given task list and user interface.
     *
     * @param ui The user interface displaying events on the task list.
     * @param storage The storage object containing task list.
     */
    @Override
    public void execute(Ui ui, Storage storage) {
        ArrayList<Task> tasks = storage.getTasks();
        ArrayList<Task> result = new ArrayList<>();
        for (Task task: tasks) {
            if (task.toString().contains(keyword)) {
                result.add(task);
            }
        }
        ui.showList(result);
    }
}