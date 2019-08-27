import commands.Command;
import commands.ExitCommand;
import commons.DukeException;
import commons.Message;
import parsers.Parser;
import storage.Storage;
import ui.Ui;
import tasks.Task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The Duke program is a simple Personal Assistant Chatbot
 * that helps a person to keep track of various things.
 *
 * @author  Jefferson111
 */
public class Duke {
    private Ui ui;
    private Storage storage;

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
        ui = new Ui();
        ui.showWelcome();
        ArrayList<Task> tasks = getStorageTasks();
        while (true) {
            String userInput = ui.readCommand();
            try {
                Command command = Parser.parse(userInput);
                command.execute(ui, tasks);
                if (command instanceof ExitCommand) {
                    break;
                }
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
        saveStorageTasks(tasks);
    }

    /**
     * Gets the tasks stored in the given filepath and converts them to list of Task objects.
     *
     * @return The list of tasks stored.
     */
    private ArrayList<Task> getStorageTasks() {
        String filePath = "tasks.txt";
        try {
            storage = new Storage(filePath);
            return storage.read();
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        } catch (FileNotFoundException e) {
            ui.showError(Message.FILE_NOT_FOUND);
        }
        return new ArrayList<>();
    }

    /**
     * Saves the tasks stored in the given filepath.
     */
    private void saveStorageTasks(ArrayList<Task> tasks) {
        try {
            storage.write(tasks);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        } catch (IOException e) {
            ui.showError(Message.FILE_NOT_SAVE);
        }
    }
}
