package duke.ui;


import duke.commands.Command;
import duke.commons.DukeException;
import duke.data.TaskList;
import duke.parser.Parser;
import duke.storage.Storage;
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
    private TaskList tasks;
    private Stoppable mainApp;

    public void initialise(Stoppable mainApp) {
        String filePath = "data/tasks.txt";
        gui = new Ui(commandInput);
        ListView<String> listView = gui.getListView();
        outputConsole.setContent(listView);
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            gui.show(gui.MESSAGE_ERROR_READING_DATA_FILE);
            tasks = new TaskList();
        }
        this.mainApp = mainApp;
        gui.showWelcome();
    }

    @FXML
    void onSend() {
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
