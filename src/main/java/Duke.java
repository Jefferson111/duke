import commands.Command;
import commands.ExitCommand;
import parsers.Parser;
import ui.Ui;
import tasks.Task;

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
            String userInput = ui.readCommand();
            try {
                Command command = Parser.parse(userInput);
                command.execute(ui, tasks);
                if (command instanceof ExitCommand) {
                    break;
                }
            } catch (IllegalArgumentException e) {
                ui.showError(e.getMessage());
            }
        }
    }
}
