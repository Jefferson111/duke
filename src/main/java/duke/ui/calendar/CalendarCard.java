package duke.ui.calendar;

import duke.data.taskList.TaskListAllTypes;
import duke.ui.UiPart;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class CalendarCard extends UiPart<StackPane> {

    @FXML
    private Label day;
    @FXML
    private Label task;
    @FXML
    private Label description;
    @FXML
    private StackPane calendarCard;

    private final TaskListAllTypes tasks;
    private static final String FXML = "CalendarCard.fxml";
    private static final String CLASSNAME = "class duke.data.task.";
    private static int currentIndex = 0;
    private boolean isCalendarCardClass = true;

    private CalendarCard(int day, TaskListAllTypes tasks) {
        super(FXML);
        this.day.setText(Integer.toString(day));
        this.tasks = tasks;
        calendarCard.getStyleClass().add("calendarCard");
        assert (tasks != null);
        if (tasks.size() > 0) {
            loadInfoOnToCard();
        }
    }

    private void loadInfoOnToCard() {
        try {
            task.setText(tasks.getTask(currentIndex).getClass().toString().replace(CLASSNAME, ""));
            description.setText(tasks.getTask(currentIndex).getDescription());
        } catch (Exception e) {

        }
    }

    public static StackPane getCalendarCard(int day, TaskListAllTypes taskListWithDates) {
        return new CalendarCard(day, taskListWithDates).getRoot();
    }

    @FXML
    private void onClick() {
        if (tasks.size() > 0) {
            currentIndex = (currentIndex + 1) % tasks.size();
            loadInfoOnToCard();
        }
    }

    @FXML
    private void onMouseEnter() {
        calendarCard.getStyleClass().add("calendarCardHover");
        calendarCard.getStyleClass().remove("calendarCard");
        isCalendarCardClass = false;
    }

    @FXML
    private void onMouseExit() {
        if (!isCalendarCardClass) {
            calendarCard.getStyleClass().add("calendarCard");
            calendarCard.getStyleClass().remove("calendarCardHover");
            isCalendarCardClass = true;
        }
    }
}
