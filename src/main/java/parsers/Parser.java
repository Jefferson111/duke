package parsers;

import commands.AddCommand;
import commands.Command;
import commands.ExitCommand;
import commands.ListCommand;
import commands.MarkDoneCommand;

/**
 * Parser for commands entered by the Duke user. It reads from standard input and
 * returns Command objects.
 */
public class Parser {
    /**
     * Parses the userInput and return a Command object.
     *
     * @param userInput The userInput read by the user interface.
     * @return The corresponding Command object.
     * @throws IllegalArgumentException If userInput is undefined.
     */
    public static Command parse(String userInput) throws IllegalArgumentException {
        String commandWord = getCommandWord(userInput);
        switch (commandWord) {
        case "bye":
            return new ExitCommand();
        case "todo":
            return new AddCommand(ParserUtil.createTodo(userInput));
        case "deadline":
            return new AddCommand(ParserUtil.createDeadline(userInput));
        case "event":
            return new AddCommand(ParserUtil.createEvent(userInput));
        case "list":
            return new ListCommand();
        case "done":
            return new MarkDoneCommand(ParserUtil.getIndex(userInput));
        default:
            throw new IllegalArgumentException("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Gets command word from the userInput.
     *
     * @param userInput The userInput read by the user interface.
     * @return The command word.
     */
    public static String getCommandWord(String userInput) {
        return userInput.strip().split(" ")[0];
    }
}
