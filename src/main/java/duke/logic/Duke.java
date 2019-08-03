package duke.logic;

import duke.commands.Command;
import duke.commons.DukeException;
import duke.data.taskList.TaskListAllTypes;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Stoppable;
import duke.ui.Ui;

public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskListAllTypes tasks;
    private Stoppable mainApp;

    public Duke(Stoppable mainApp, Ui ui) {
        initialise(mainApp, ui);
        ui.showWelcome();
    }

    private void initialise(Stoppable mainApp, Ui ui) {
        String filePath = "data/tasks.txt";
        initialiseStorage(filePath);
        initialiseUi(ui);
        this.mainApp = mainApp;
    }

    private void initialiseUi(Ui ui) {
        this.ui = ui;
    }

    private void initialiseStorage(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskListAllTypes(storage.load());
        } catch (DukeException e) {
            ui.show(ui.MESSAGE_ERROR_READING_DATA_FILE);
            tasks = new TaskListAllTypes();
        }
    }

    public void getResponse(String userInput) {
        try {
            Command c = Parser.parse(userInput);
            c.execute(tasks, ui, storage);
            if (c.isExit()) {
                try {
                    exitApp();
                } catch (Exception e) {
                    ui.showError("Exit app failed" + e.getMessage());
                }
            }
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    private void exitApp() throws Exception {
        mainApp.stop();
    }

}
