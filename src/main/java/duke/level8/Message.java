package duke.level8;

import java.util.List;

public class Message {
    private static final String LINE = "____________________________________________________________";
    private static String PREFIX = "     ";
    public static final String MESSAGE_UNKNOWN_COMMAND =
            "I'm sorry, but I don't know what that means :-(";
    public static final String MESSAGE_ERROR_READING_DATA_FILE =
            "Problem reading file. Starting with an empty task list";
    public static String  logo =
            PREFIX + " ____        _        \n" +
            PREFIX + "|  _ \\ _   _| | _____ \n" +
            PREFIX + "| | | | | | | |/ / _ \\\n" +
            PREFIX + "| |_| | |_| |   <  __/\n" +
            PREFIX + "|____/ \\__,_|_|\\_\\___|\n\n";

    public static final String MESSAGE_ERROR = "\u2639 OOPS!!! %s\n";
    public static final String MESSAGE_LIST_SIZE = "Now you have %d tasks in the list.\n";
    public static final String MESSAGE_ADDITION = "Got it. I've added this task: \n" + PREFIX + "  %s\n";
    public static final String MESSAGE_DELETION = "Noted. I've removed this task: \n" + PREFIX + "  %s\n";
    public static final String MESSAGE_MARK_AS_DONE = "Nice! I've marked this task as done: \n" + PREFIX + "  %s\n";
    public static final String MESSAGE_EXIT = "Bye. Hope to see you again soon!\n";
    public static final String MESSAGE_LIST = "Here are the tasks in your list:\n";

    public static String getError(String message) {
        return String.format(MESSAGE_ERROR, message);
    }

    public static String getListSize(int size){
        return String.format(MESSAGE_LIST_SIZE, size);
    }

    public static String getAddition(Task t){
        return String.format(MESSAGE_ADDITION, t);
    }

    public static String getDeletion(Task t){
        return String.format(MESSAGE_DELETION, t);
    }

    public static String getMarkAsDone(Task t){
        return String.format(MESSAGE_MARK_AS_DONE, t);
    }

    public static String getTaskList(List<String> taskDescriptions){
        StringBuilder message = new StringBuilder();
        message.append(MESSAGE_LIST);
        for (String s: taskDescriptions){
            message.append(PREFIX + s + "\n");
        }
        return message.toString();
    }

    public static String getExitMessage() {
        return MESSAGE_EXIT;
    }

    public static String getWelcome() {
        StringBuilder message = new StringBuilder();
        message.append(getLine());
        message.append(logo);
        message.append(PREFIX + "Hello! I'm Duke\n");
        message.append(PREFIX + "What can I do for you?\n");
        message.append(PREFIX + getLine());
        return message.toString();
    }

    public static String getLine() {
        return (LINE + "\n");
    }
}
