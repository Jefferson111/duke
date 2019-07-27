package duke.commands;

import java.util.List;

import duke.ui.Ui;
import duke.storage.Storage;
import duke.data.taskList.TaskListAllTypes;
import duke.commons.DukeException;

public class FindCommand extends Command {

    TaskListAllTypes filteredTasks;
    String[] keywords;

    public FindCommand(String[] keywords) {
        filteredTasks = new TaskListAllTypes();
        this.keywords = keywords;
    }

    @Override
    public void execute(TaskListAllTypes tasks, Ui ui, Storage storage) throws DukeException {
        findTasksWithKeywords(tasks, tasks.getTaskListDescriptions());
        ui.showTaskList(filteredTasks.getTaskListDescriptions());
    }

    private void findTasksWithKeywords(TaskListAllTypes tasks, List<String> taskListDescriptions) throws DukeException {
        for (int i = 0; i < taskListDescriptions.size(); ++i) {
            for (String keyword : keywords) {
                if (taskListDescriptions.get(i).contains(keyword)) {
                    filteredTasks.addTask(tasks.getTask(i));
                }
            }
        }
    }

}
