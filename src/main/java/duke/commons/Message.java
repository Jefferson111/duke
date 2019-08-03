package duke.commons;

import java.util.List;
import duke.data.task.Task;

public class Message {
    public static final String MESSAGE_UNKNOWN_COMMAND =
            "I'm sorry, but I don't know what that means :-(";
    public static final String MESSAGE_ERROR_READING_DATA_FILE =
            "Problem reading file. Starting with an empty task list";

    public static final String MESSAGE_ERROR = "\u2639 OOPS!!! %s\n";
    public static final String MESSAGE_LIST_SIZE = "Now you have %d tasks in the list.\n";
    public static final String MESSAGE_ADDITION = "Got it. I've added this task: \n" + "  %s\n";
    public static final String MESSAGE_DELETION = "Noted. I've removed this task: \n" + "  %s\n";
    public static final String MESSAGE_MARK_AS_DONE = "Nice! I've marked this task as done: \n" + "  %s\n";
    public static final String MESSAGE_EXIT = "Bye. Hope to see you again soon!\n";
    public static final String MESSAGE_LIST = "Here are the tasks in your list:\n";
    public static final String MESSAGE_SNOOZE = "Alright! I've rescheduled this task: \n" + "  %s\n";
    public static final String MESSAGE_HELP = "Here are the commands you can type:\n";

    public static final String[] MESSAGE_USAGE = {"todo: Adds a todo to Duke\n" + "Parameters: todo TASK\n" + "Example: todo Catch Fish\n",
            "deadline: Adds a deadline to Duke\n" + "Parameters: deadline TASK /by DESCRIPTOR\n" + "Example: deadline homework /by tomorrow\n",
            "event: Adds an event to Duke\n" + "Parameters: event TASK /at DESCRIPTOR\n" + "Example: event Exam /at Multi-Purpose-Hall\n",
            "weekly: Adds a weekly task to Duke\n" + "Parameters: weekly TASK /on DAY_OF_WEEK\n" + "Example: weekly Meeting /on Monday\n",
            "list: Show the current list of tasks\n" + "Parameters: list\n" + "Example: list\n",
            "delete: Remove a task from Duke\n" + "Parameters: delete INDEX\n" + "Example: delete 1\n",
            "done: Mark a task as done on Duke\n" + "Parameters: done INDEX\n" + "Example: done 2\n",
            "find: Find tasks from Duke\n" + "Parameters: find KEYWORD...\n" + "Example: find meeting\n",
            "bye: Close Duke\n" + "Parameters: bye\n" + "Example: bye\n"
    };

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

    public static String getSnooze(Task t){
        return String.format(MESSAGE_SNOOZE, t);
    }

    public static String getTaskList(List<String> taskDescriptions){
        StringBuilder message = new StringBuilder();
        message.append(MESSAGE_LIST);
        for (String s: taskDescriptions){
            message.append(s + "\n");
        }
        return message.toString();
    }

    public static String getExitMessage() {
        return MESSAGE_EXIT;
    }

    public static String getHelp() {
        StringBuilder message = new StringBuilder();
        message.append(MESSAGE_HELP + "\n");
        for (String s: MESSAGE_USAGE){
            message.append(s + "\n");
        }
        return message.toString();
    }

    public static String getWelcome() {
        StringBuilder message = new StringBuilder();
        message.append("Hello! I'm Duke\n");
        message.append("What can I do for you?\n");
        return message.toString();
    }
}
