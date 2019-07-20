package duke.commands;

import java.util.List;
import java.util.ArrayList;

import duke.ui.Ui;
import duke.storage.Storage;
import duke.data.TaskList;
import duke.commons.DukeException;

public class FindCommand extends Command {

    TaskList filteredTasks;
    List<String> keywords;

    public FindCommand(String[] words) {
        filteredTasks = new TaskList();
        keywords = new ArrayList<>();
        for (String word : words) {
            keywords.add(word);
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        findTasksWithKeywords(tasks, tasks.getTaskListDescriptions());
        ui.showTaskList(filteredTasks.getTaskListDescriptions());
    }

    private void findTasksWithKeywords(TaskList tasks, List<String> taskListDescriptions) throws DukeException {
        for (int i = 0; i < taskListDescriptions.size(); ++i) {
            for (String keyword : keywords) {
                if (taskListDescriptions.get(i).contains(keyword)) {
                    filteredTasks.addTask(tasks.getTask(i));
                }
            }
        }
    }

}
