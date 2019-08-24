package tasks;

import java.time.LocalDateTime;

/**
 * Class representing a deadline.
 */
public class Deadline extends Task {
    private LocalDateTime startDate;
    private String deadline;

    /**
     * Initializes a deadline not yet done with the given description and a date.
     *
     * @param description A description of this deadline.
     */
    public Deadline(String description, LocalDateTime startDate) {
        super(description);
        this.startDate = startDate;
    }

    /**
     * Initializes a deadline not yet done with the given description.
     *
     * @param description A description of this deadline.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Returns a string representation of this deadline.
     *
     * @return The desired string representation.
     */
    @Override
    public String toString() {
        return "[D]" + (isDone ? "[✓] " : "[✘] ") + description + " (by: " + deadline + ")";
    }
}
