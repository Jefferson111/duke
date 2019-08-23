package task;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime startDate;

    /**
     * Initializes a deadline not yet done with the given description and a date.
     *
     * @param description A description of this deadline.
     */
    public Deadline(String description, LocalDateTime startDate) {
        this(description);
        this.startDate = startDate;
    }

    /**
     * Initializes a deadline not yet done with the given description.
     *
     * @param description A description of this deadline.
     */
    public Deadline(String description) {
        super(description);
    }

    /**
     * Returns a string representation of this deadline.
     *
     * @return The desired string representation.
     */
    @Override
    public String toString() {
        return "[D]" + (isDone ? "[✓] " : "[✘]") + description;
    }
}
