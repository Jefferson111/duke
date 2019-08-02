package duke.data.task;

import duke.commons.DukeException;

import java.util.Calendar;
import java.util.Date;

public abstract class TaskWithDates extends Task {
    protected Date date;

    public TaskWithDates(String description) {
        super(description);
    }

    public void snooze(int day) throws DukeException {
        Calendar calendar = Calendar.getInstance();
        if (date == null) {
            throw new DukeException("No date field in task!");
        }
        calendar.setTime(date); // Now use today date.
        calendar.add(Calendar.DATE, day);
        date = calendar.getTime();
        updateOtherDescriptions();
    }

    public Date getDate() { return date; }

    public abstract void updateOtherDescriptions();

}
