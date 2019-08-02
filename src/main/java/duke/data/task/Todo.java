package duke.data.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
