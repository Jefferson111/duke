package duke.ui;

import duke.commons.Message;
import duke.data.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.Scanner;

public class Ui {

    private static final String LINE = "____________________________________________________________";
    static String PREFIX = "     ";
    public static final String MESSAGE_UNKNOWN_COMMAND =
            "I'm sorry, but I don't know what that means :-(";
    public static final String MESSAGE_ERROR_READING_DATA_FILE =
            "Problem reading file. Starting with an empty task list";
    
    private ListView<String> listView;
    private ObservableList<String> content;

    @FXML
    private TextField commandInput;

    public Ui(TextField commandInput) {
        this.commandInput = commandInput;
        content = FXCollections.observableArrayList();
        listView = new ListView<>(content);
        listView.setMaxHeight(Double.POSITIVE_INFINITY);
        listView.setMaxWidth(Double.POSITIVE_INFINITY);
    }

    public ListView<String> getListView() {
        return listView;
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

    public String readCommand() {
        String line = commandInput.getText();
        clearCommandInput();
        content.add("\n" + line);
        return line.trim();
    }

    /** Clears the command input box */
    private void clearCommandInput() {
        commandInput.setText("");
    }

    public void showExitMessage() {
        show(Message.getExitMessage());
    }

    public void showLine(){
        content.add("    " + LINE);
    }

    public void show(String msg){
        content.add(PREFIX + msg);
    }
}
