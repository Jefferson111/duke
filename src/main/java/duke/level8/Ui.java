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
        showLine();
        String logo =    " ____        _        \n" +
                PREFIX + "|  _ \\ _   _| | _____ \n" +
                PREFIX + "| | | | | | | |/ / _ \\\n" +
                PREFIX + "| |_| | |_| |   <  __/\n" +
                PREFIX + "|____/ \\__,_|_|\\_\\___|\n";
        show(logo);
        show("Hello! I'm Duke");
        show("What can I do for you?");
        showLine();
    }

    public void showError(String message) {
        show("☹ OOPS!!! " + message);
    }
    
    public void showListSize(int size){
        show("Now you have " + size + " tasks in the list.");
    }
    
    public void showAddition(Task t){
        show("Got it. I've added this task: \n  " + PREFIX + t);
    }
    
    public void showDeletion(Task t){
        show("Noted. I've removed this task: \n  " + PREFIX + t);
    }
    
    public void showMarkAsDone(Task t){
        show("Nice! I've marked this task as done: \n  " + PREFIX + t);
    }
    
    public void showTaskList(List<String> taskDescriptions){
        show("Here are the tasks in your list:");
        for (String s: taskDescriptions){
            show(s);
        }
    }

    public String readCommand() {
        String line = in.nextLine().trim();
        System.out.println("\n" + line);
        return line.trim();
    }

    public void showExitMessage() {
        show("Bye. Hope to see you again soon!");
    }
    
    public void showLine(){
        System.out.println("    " + LINE);
    }
    
    public void show(String msg){
        System.out.println(PREFIX + msg);
    }
}
