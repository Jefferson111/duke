package duke.level8;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone(boolean status) {
        isDone = status;
    }

    @Override
    public String toString() {
        return (isDone ? "[\u2713] " : "[\u2718] ") + description;
    }
}
