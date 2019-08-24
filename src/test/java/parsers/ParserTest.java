package parsers;

import commands.AddCommand;
import commands.ExitCommand;
import commands.ListCommand;
import commands.MarkDoneCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {

    @Test
    void parse() {
        assertTrue(Parser.parse("bye") instanceof ExitCommand);
        assertTrue(Parser.parse("todo homework") instanceof AddCommand);
        assertTrue(Parser.parse("deadline homework /by tomorrow") instanceof AddCommand);
        assertTrue(Parser.parse("event exam /at classroom") instanceof AddCommand);
        assertTrue(Parser.parse("list") instanceof ListCommand);
        assertTrue(Parser.parse("done 1") instanceof MarkDoneCommand);
    }

    @Test
    void getCommandWord() {
        assertEquals(Parser.getCommandWord("todo homework"), "todo");
    }
}