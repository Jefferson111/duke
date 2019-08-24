package parsers;

import tasks.Deadline;
import tasks.Event;
import tasks.Todo;

public class ParserUtil {
    /**
     * Parses the userInput and return a new to-do constructed from it.
     *
     * @param userInput The userInput read by the user interface.
     * @return The new to-do object.
     */
    public static Todo createTodo(String userInput) {
        String description = userInput.substring("todo".length()).strip();
        return new Todo(description);
    }

    /**
     * Parses the userInput and return a new deadline constructed from it.
     *
     * @param userInput The userInput read by the user interface.
     * @return The new deadline object.
     */
    public static Deadline createDeadline(String userInput) {
        String[] deadlineDetails = userInput.substring("deadline".length()).strip().split("/by");
        return new Deadline(deadlineDetails[0].strip(), deadlineDetails[1].strip());
    }

    /**
     * Parses the userInput and return a new event constructed from it.
     *
     * @param userInput The userInput read by the user interface.
     * @return The new event object.
     */
    public static Event createEvent(String userInput) {
        String[] eventDetails = userInput.substring("event".length()).strip().split("/at");
        return new Event(eventDetails[0].strip(), eventDetails[1].strip());
    }

    /**
     * Parses the userInput and return an index extracted from it.
     *
     * @param userInput The userInput read by the user interface.
     * @return The index.
     */
    public static int getIndex(String userInput) {
        int index = Integer.parseInt(userInput.replaceAll("\\D+", ""));
        return index - 1;
    }
}
