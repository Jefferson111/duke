package duke.data.task;

public class Weekly extends Task {

    protected String on;

    public Weekly(String description, String on) {
        super(description);
        this.on = on;
    }

    public String getOn() {
        return on;
    }
    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return "[W]" + super.toString() + " (every: " + on + ")";
    }
}
