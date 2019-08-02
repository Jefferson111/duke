package duke.ui;

import duke.commands.Command;
import duke.commons.DukeException;
import duke.data.taskList.TaskListAllTypes;
import duke.parser.Parser;
import duke.storage.Storage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

public class MainWindow {
    @FXML
    private ScrollPane outputConsole;

    @FXML
    private TextField commandInput;

    private Ui gui;
    private Storage storage;
    private TaskListAllTypes tasks;
    private Stoppable mainApp;

    public void initialise(Stoppable mainApp) {
        String filePath = "data/tasks.txt";
        initialiseGui();
        initialiseStorage(filePath);
        this.mainApp = mainApp;
        gui.showWelcome();
    }

    private void initialiseStorage(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskListAllTypes(storage.load());
        } catch (DukeException e) {
            gui.show(gui.MESSAGE_ERROR_READING_DATA_FILE);
            tasks = new TaskListAllTypes();
        }
    }

    private void initialiseGui() {
        gui = new Ui(commandInput);
        ListView<String> listView = gui.getListView();
        outputConsole.setContent(listView);
    }

    @FXML
    void onSend(ActionEvent event) {
        runDuke();
    }

    @FXML
    void onEnter(ActionEvent event) {
        runDuke();
    }

    private void runDuke() {
        try {
            String fullCommand = gui.readCommand();
            gui.showLine();
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, gui, storage);
            if (c.isExit()) {
                try {
                    exitApp();
                } catch (Exception e) {
                    gui.showError("Exit app failed" + e.getMessage());
                }
                return;
            }
        } catch (DukeException e) {
            gui.showError(e.getMessage());
        } finally {
            gui.showLine();
        }
    }

    private void exitApp() throws Exception {
        mainApp.stop();
    }

}
