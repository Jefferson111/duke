package task;

import java.time.LocalDate;

public class Event extends Task {
    private LocalDate startDate;

    /**
     * Initializes a event not yet done with the given description.
     *
     * @param description A description of this event.
     */
    public Event(String description, LocalDate startDate) {
        super(description);
        this.startDate = startDate;
    }

    /**
     * Returns a string representation of this event.
     *
     * @return The desired string representation.
     */
    @Override
    public String toString() {
        return "[E]" + (isDone ? "[✓] " : "[✘]") + description;
    }
}
