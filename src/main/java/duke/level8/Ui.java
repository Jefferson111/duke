package duke.level8;

import java.util.List;
import java.util.Scanner;

public class Ui {

    private static final String LINE = "____________________________________________________________";
    static String PREFIX = "     ";
    public static final String MESSAGE_UNKNOWN_COMMAND = 
            "I'm sorry, but I don't know what that means :-(";
    public static final String MESSAGE_ERROR_READING_DATA_FILE = 
            "Problem reading file. Starting with an empty task list";

    private Scanner in;

    public Ui() {
        in = new Scanner(System.in);
    }

    public void showWelcome() {
        show(Message.getWelcome());
    }

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
    
    public void showTaskList(List<String> taskDescriptions){
        show(Message.getTaskList(taskDescriptions));
    }

    public String readCommand() {
        String line = in.nextLine().trim();
        System.out.println("\n" + line);
        return line.trim();
    }

    public void showExitMessage() {
        show(Message.getExitMessage());
    }
    
    public void showLine(){
        System.out.println("    " + LINE);
    }
    
    public void show(String msg){
        System.out.println(PREFIX + msg);
    }
}
