package task;

import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime startDate;

    /**
     * Initializes a event not yet done with the given description and a date.
     *
     * @param description A description of this event.
     */
    public Event(String description, LocalDateTime startDate) {
        this(description);
        this.startDate = startDate;
    }

    /**
     * Initializes a event not yet done with the given description.
     *
     * @param description A description of this event.
     */
    public Event(String description) {
        super(description);
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
