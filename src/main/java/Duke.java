import java.util.ArrayList;

/**
 * The Duke program is a simple Personal Assistant Chatbot
 * that helps a person to keep track of various things.
 *
 * @author  Jefferson111
 */
public class Duke {
    /**
     * Entry point.
     */
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        Ui ui = new Ui();
        ui.showWelcome();
        while (true) {
            String command = ui.readCommand();
            if (command.equals("list")) {
                ui.showList(tasks);
            } else if (command.equals("bye")) {
                break;
            } else if (command.substring(0, "done".length()).equals("done")) {
                markDoneCommand(tasks, ui, command);
            } else {
                tasks.add(new Task(command));
                ui.showAdd(command);
            }
        }
        ui.showBye();
    }

    /**
     * Marks a task as done.
     *
     * @param tasks The ArrayList containing all the tasks.
     * @param ui The ui of Duke.
     * @param command The user input in String representation.
     */
    private static void markDoneCommand(ArrayList<Task> tasks, Ui ui, String command) {
        int index = Integer.parseInt(command.replaceAll("\\D+", "")); //remember handle exceptions later
        Task task = tasks.get(index - 1);
        task.setDone(true);
        ui.showMarkDone(task);
    }
}
