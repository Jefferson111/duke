package duke.data.task;

import duke.logic.parser.TimeParser;

import java.util.Date;

public class Deadline extends TaskWithDates {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.date = null;
    }

    public Deadline(String description, Date date) {
        super(description);
        this.date = date;
        this.by = TimeParser.parseDateToString(date);
    }

    public String getBy() {
        return by;
    }
    public boolean isDone() {
        return isDone;
    }

    @Override
    public void updateOtherDescriptions() {
        by = TimeParser.parseDateToString(date);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
