package duke.data.task;

import java.time.DayOfWeek;

public class Weekly extends Task {

    protected DayOfWeek on;

    public Weekly(String description, DayOfWeek on) {
        super(description);
        this.on = on;
    }

    public Weekly(String description, String on) {
        super(description);
        this.on = DayOfWeek.valueOf(on);
    }

    public DayOfWeek getOn() {
        return on;
    }
    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return "[W]" + super.toString() + " (every: " + on.toString() + ")";
    }
}
