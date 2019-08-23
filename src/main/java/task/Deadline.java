package task;

import java.time.LocalDate;

public class Deadline extends Task {
    private LocalDate startDate;

    /**
     * Initializes a deadline not yet done with the given description.
     *
     * @param description A description of this deadline.
     */
    public Deadline(String description, LocalDate startDate) {
        super(description);
        this.startDate = startDate;
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
