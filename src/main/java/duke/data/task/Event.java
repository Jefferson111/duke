package duke.data.task;

import duke.logic.parser.TimeParser;

import java.util.Date;

public class Event extends TaskWithDates {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.date = null;
    }

    public Event(String description, Date date) {
        super(description);
        this.date = date;
        this.at = TimeParser.parseDateToString(date);
    }

    public String getAt() {
        return at;
    }
    public boolean isDone() {
        return isDone;
    }

    @Override
    public void updateOtherDescriptions() {
        at = TimeParser.parseDateToString(date);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
