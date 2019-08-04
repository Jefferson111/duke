package duke.ui;

import duke.DukeApp;
import duke.logic.Logic;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
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

    private Image user = new Image(this.getClass().getResourceAsStream("/images/User.jpg"));
    private Logic logic;
    private DukeApp dukeApp;

    @FXML
    public void initialise(DukeApp dukeApp) {
        this.dukeApp = dukeApp;
        Ui ui = new Ui(dialogContainer);
        logic = new Logic(dukeApp, ui);
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
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
            dialogContainer.getChildren().addAll(
                    ImageDialogBox.getUserDialog(new Image(imageFile.toURI().toString()), user)
            );
            logic.getImageResponse(imageFile);
        } catch (Exception e) {
            //user cancelled operation, do nothing
        }
    }

    private File getFile() throws Exception {
        FileChooser.ExtensionFilter imageFilter
                = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(imageFilter);
        return fc.showOpenDialog(dukeApp.getMainStage());
    }
}
