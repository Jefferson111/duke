package duke.ui;

import duke.DukeApp;
import duke.logic.Logic;
import duke.ui.calendar.CalendarWindow;
import duke.ui.game.MainGameWindow;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends UiPart<Stage> {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    @FXML
    private Button sendImageButton;

    private static final String FXML = "MainWindow.fxml";
    private Stage primaryStage;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/User.jpg"));
    private Logic logic;
    private CalendarWindow calendarWindow;
    //private MainGameWindow mainGameWindow;

    public MainWindow(Stage primaryStage) {
        super(FXML, primaryStage);
        this.primaryStage = primaryStage;
    }

    /**
     * Initialises the application by instantiating the logic and ui objects
     *
     * @param dukeApp The main application itself; to allow logic to stop the application
     */
    public void initialise(DukeApp dukeApp) {
        Ui ui = new Ui(dialogContainer);
        logic = new Logic(dukeApp, ui);

        //random stuff
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        calendarWindow = new CalendarWindow(logic.getTasks());
        //mainGameWindow = new MainGameWindow();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        //Echo user input
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, user)
        );
        logic.getResponse(input);
        userInput.clear();
    }

    @FXML
    private void handleUserImageInput() {
        try {
            File imageFile = getFile();

            //Echo user input
            dialogContainer.getChildren().addAll(
                    ImageDialogBox.getUserDialog(new Image(imageFile.toURI().toString()), user)
            );
            logic.getImageResponse(imageFile);
        } catch (Exception e) {
            //user cancelled operation, do nothing
        }
    }

    @FXML
    private void handleGame() {
        /*
        if (mainGameWindow.isShowing()) {
            mainGameWindow.focus();
        } else {
            mainGameWindow.show();
        }
         */
    }

    @FXML
    private void handleCalendar() {
        if (calendarWindow.isShowing()) {
            calendarWindow.focus();
        } else {
            calendarWindow.show();
        }
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        logic.tryExitApp();
    }

    /**
     * Shows the application.
     */
    public void show() {
        primaryStage.show();
    }

    /**
     * Gets the file in which an image (*.jpg or *.png) resides in using FileChooser.
     *
     * @return File containing the user-selected image
     * @throws Exception Not sure
     */
    private File getFile() throws Exception {
        FileChooser.ExtensionFilter imageFilter
                = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(imageFilter);
        return fc.showOpenDialog(primaryStage);
    }
}
