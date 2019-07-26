package duke.parser;

import duke.commands.*;
import duke.data.*;
import duke.ui.Ui;
import duke.commons.DukeException;

import java.util.Date;

public class Parser {
    
    public static Command parse(String line) throws DukeException {
        String commandWord = Parser.getCommandWord(line);
        switch (commandWord) {
            case "bye":
                return new ExitCommand();
            case "todo":
                return new AddCommand(createTodo(line));
            case "deadline":
                return new AddCommand(createDeadline(line));
            case "delete":
                return new DeleteCommand(getIndex(line));
            case "event":
                return new AddCommand(createEvent(line));
            case "weekly":
                return new AddCommand(createWeekly(line));
            case "list":
                return new ListCommand();
            case "done":
                return new MarkCommand(getIndex(line));
            case "find":
                return new FindCommand(createKeyWords(line));
            case "snooze":
                return new SnoozeCommand(getIndex(line), getDay(line));
        }
        throw new DukeException(Ui.MESSAGE_UNKNOWN_COMMAND);
    }

    public static String[] createKeyWords(String line) {
        return  line.substring("find".length()).strip().split(" ");
    }

    public static String getCommandWord(String line) {
        return line.strip().split(" ")[0];
    }

    public static Todo createTodo(String line) throws DukeException {
        String description = line.substring("todo".length()).strip();
        if (description.isEmpty()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        return new Todo(description);
    }

    public static Deadline createDeadline(String line) throws DukeException {
        String[] deadlineDetails = line.substring("deadline".length()).strip().split("/by");
        if (deadlineDetails.length != 2 || deadlineDetails[1] == null) {
            throw new DukeException("Invalid Deadline format.");
        }
        if (deadlineDetails[0].strip().isEmpty()) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
        Date date = TimeParser.parseStringToDate(deadlineDetails[1].strip());
        if (date == null) {
            return new Deadline(deadlineDetails[0].strip(), deadlineDetails[1].strip());
        }
        return new Deadline(deadlineDetails[0].strip(), date);
    }

    public static Event createEvent(String line) throws DukeException {
        String[] eventDetails = line.substring("event".length()).strip().split("/at");
        if (eventDetails.length != 2 || eventDetails[1] == null) {
            throw new DukeException("Invalid event format.");
        }
        if (eventDetails[0].strip().isEmpty()) {
            throw new DukeException("The description of an event cannot be empty.");
        }
        Date date = TimeParser.parseStringToDate(eventDetails[1].strip());
        if (date == null) {
            return new Event(eventDetails[0].strip(), eventDetails[1].strip());
        }
        return new Event(eventDetails[0].strip(), date);
    }

    public static Weekly createWeekly(String line) throws DukeException {
        String[] weeklyDetails = line.substring("weekly".length()).strip().split("/on");
        if (weeklyDetails.length != 2 || weeklyDetails[1] == null) {
            throw new DukeException("Invalid weekly format.");
        }
        if (weeklyDetails[0].strip().isEmpty()) {
            throw new DukeException("The description of a weekly cannot be empty.");
        }
        String day = TimeParser.parseStringToDay(weeklyDetails[1].strip());
        return new Weekly(weeklyDetails[0].strip(), day);
    }


    public static int getIndex(String line) throws DukeException {
        try {
            String index = line.split(" ")[1].strip();
            return Integer.parseInt(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please indicate the index of the task.");
        } catch (NumberFormatException e) {
            throw new DukeException("Index have to be number!");
        }
    }

    public static int getDay(String line) throws DukeException {
        String[] snoozeDetails = line.substring("snooze".length()).strip().split("/by");
        if (snoozeDetails.length != 2 || snoozeDetails[1] == null) {
            throw new DukeException("Invalid snooze format.");
        }
        try {
            return Integer.parseInt(snoozeDetails[1].strip());
        } catch (NumberFormatException e) {
            throw new DukeException("Please indicate in numerical form the number of days to snooze.");
        }
    }

    public static Task createTaskFromStorageLine(String line) {
        String[] taskParts = line.split("\\|");
        String type = taskParts[0].strip();
        String status = taskParts[1].strip();
        String description = taskParts[2].strip();
        Task t;
        if (type.equals("T")) {
            t = new Todo(description);
        } else if (type.equals("D")) {
            Date date = TimeParser.parseStoredStringToDate(taskParts[3].strip());
            if (date != null) {
                t = new Deadline(description, date);
            } else {
                t = new Deadline(description, taskParts[3].strip());
            }
        } else if (type.equals("W")) {
            t = new Weekly(description, taskParts[3].strip());
        }
        else {
            Date date = TimeParser.parseStoredStringToDate(taskParts[3].strip());
            if (date != null) {
                t = new Event(description, date);
            } else {
                t = new Event(description, taskParts[3].strip());
            }
        }
        t.setDone(status.equals("true"));
        return t;
    }
}
