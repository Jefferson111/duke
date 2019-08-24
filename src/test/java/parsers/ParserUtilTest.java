package parsers;

import org.junit.jupiter.api.Test;
import tasks.Deadline;
import tasks.Event;
import tasks.Todo;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserUtilTest {

    @Test
    void createTodo() {
        assertTrue(ParserUtil.createTodo("Homework") instanceof Todo);
    }

    @Test
    void createDeadline() {
        assertTrue(ParserUtil.createDeadline("deadline homework /by tomorrow") instanceof Deadline);
    }

    @Test
    void createEvent() {
        assertTrue(ParserUtil.createEvent("event exam /at classroom") instanceof Event);
    }

    @Test
    void getIndex() {
        assertEquals(ParserUtil.getIndex("done 1"), 0);
    }
}