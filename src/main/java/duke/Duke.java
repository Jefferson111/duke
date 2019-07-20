package duke;

import duke.commands.*;
import duke.commons.DukeException;
import duke.ui.Ui;
import duke.data.TaskList;
import duke.storage.Storage;
import duke.parser.Parser;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.show(ui.MESSAGE_ERROR_READING_DATA_FILE);
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        System.out.flush();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
