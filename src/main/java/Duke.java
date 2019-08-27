import commands.Command;
import commands.ExitCommand;
import commons.DukeException;
import parsers.Parser;
import storage.Storage;
import ui.Ui;

/**
 * The Duke program is a simple Personal Assistant Chatbot
 * that helps a person to keep track of various things.
 *
 * @author  Jefferson111
 */
public class Duke {
    private static  final String FILE_PATH = "tasks.txt";

    /**
     * Entry point.
     */
    public static void main(String[] args) {
        new Duke();
    }

    /**
     * Creates Duke instance.
     */
    private Duke() {
        Ui ui = new Ui();
        ui.showWelcome();
        Storage storage = new Storage(FILE_PATH, ui);
        while (true) {
            String userInput = ui.readCommand();
            try {
                Command command = Parser.parse(userInput);
                command.execute(ui, storage);
                if (command instanceof ExitCommand) {
                    break;
                }
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }
}
