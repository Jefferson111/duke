package duke.ui;

import duke.commons.Message;
import duke.data.task.Task;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import java.util.List;

public class Ui {

    static String PREFIX = "     ";
    public static final String MESSAGE_UNKNOWN_COMMAND =
            "I'm sorry, but I don't know what that means :-(";
    public static final String MESSAGE_ERROR_READING_DATA_FILE =
            "Problem reading file. Starting with an empty task list";

    @FXML
    private VBox dialogContainer;

    private Image duke = new Image(this.getClass().getResourceAsStream("/images/Duke.jpg"));

    public Ui(VBox dialogContainer) {
        this.dialogContainer = dialogContainer;
    }

    public void showWelcome() {
        show(Message.getWelcome());
    }

    public void showHelp() { show(Message.getHelp()); }

    public void showError(String message) {
        show(Message.getError(message));
    }

    public void showListSize(int size){
        show(Message.getListSize(size));
    }

    public void showAddition(Task t){
        show(Message.getAddition(t));
    }

    public void showDeletion(Task t){
        show(Message.getDeletion(t));
    }

    public void showMarkAsDone(Task t){
        show(Message.getMarkAsDone(t));
    }

    public void showSnooze(Task t){
        show(Message.getSnooze(t));
    }

    public void showTaskList(List<String> taskDescriptions){
        show(Message.getTaskList(taskDescriptions));
    }

    public void showExitMessage() {
        show(Message.getExitMessage());
    }

    public void show(String msg){
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(msg, duke)
        );
    }
}
