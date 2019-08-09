package duke.ui.calendar;

import duke.commons.DukeException;
import duke.data.task.Task;
import duke.data.task.TaskWithDates;
import duke.data.task.Weekly;
import duke.data.taskList.TaskListAllTypes;

import duke.ui.UiPart;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Date;

/**
 * Controller for a calendar
 */
public class CalendarWindow extends UiPart<Stage> {

    private static final String FXML = "CalendarWindow.fxml";
    private YearMonth currentYearMonth;
    private int numOfDays;
    private int dayOfTheFirstDay;
    private String currentMonth;
    private TaskListAllTypes[] taskListWithDates;
    private static TaskListAllTypes tasks;
    private static final int MAX_ROW = 6;
    private static final int MAX_COL = 7;

    @FXML
    private VBox pane;
    @FXML
    private Text calendarTitle;
    @FXML
    private GridPane gridCalendar;

    /**
     * Refreshes the calendar
     */
    private void refreshCalendar() {
        updateVariables();
        setCalendarTitle(currentYearMonth.getYear(), currentMonth);
        fillCalendarDays();
    }

    /**
     * Sets the title of the calendar according to a specific month and year.
     */
    public void setCalendarTitle(int year, String month) {
        calendarTitle.setText("\u2652" + month + " " + year + "\u2652");
        pane.setId(currentMonth);
    }

    /**
     * Fills up the calendar with the necessary information
     */
    private void fillCalendarDays() {
        gridCalendar.getChildren().clear();
        boolean flag = false;
        int day = 1;
        for (int i = 0; i < MAX_ROW && day <= numOfDays; i++) {
            for (int j = 0; j < MAX_COL && day <= numOfDays; j++) {
                if (dayOfTheFirstDay == j) {
                    flag = true;
                }
                if (flag) {
                    gridCalendar.add(CalendarCard.getCalendarCard(day, taskListWithDates[day]), j, i);
                    ++day;
                }
            }
        }
    }

    /**
     * Updates the relevant variables to contain information of the particular month
     * that is to be displayed
     */
    private void updateVariables() {
        setCalendarTitle(currentYearMonth.getYear(), currentMonth);
        currentMonth = currentYearMonth.getMonth().toString();
        numOfDays = currentYearMonth.lengthOfMonth();
        findRelevantTasks();
        dayOfTheFirstDay = currentYearMonth.atDay(1).getDayOfWeek().getValue() % 7;
    }

    /**
     * Finds the tasks that needs to be displayed
     */
    private void findRelevantTasks() {
        taskListWithDates = new TaskListAllTypes[numOfDays + 1];
        for (int i = 0; i <= numOfDays; ++i) {
            taskListWithDates[i] = new TaskListAllTypes();
        }
        for (int i = 0; i < tasks.size(); ++i) {
            try {
                Task t = tasks.getTask(i);
                tryAddingTask(t);
            } catch (DukeException e) {
                //
            }
        }
    }

    /**
     * Tries to add a task to the current calendar
     *
     * @param t a task from the Duke's task list
     * @throws DukeException if the task cannot be added
     */
    private void tryAddingTask(Task t) throws DukeException {
        if (t instanceof TaskWithDates && ((TaskWithDates) t).getDate() != null) {
            Object date = ((TaskWithDates) t).getDate();
            if (date instanceof Date) {
                date = ((Date) date).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            }
            assert (date instanceof LocalDate);
            if (isSameYearMonth(date)) {
                taskListWithDates[((LocalDate) date).getDayOfMonth()].addTask(t);
            }
        } else if (t instanceof Weekly) {
            for (int i = 1; i <= numOfDays; ++i) {
                if (((Weekly) t).getOn() == currentYearMonth.atDay(i).getDayOfWeek()) {
                    taskListWithDates[i].addTask(t);
                }
            }
        }
    }

    private boolean isSameYearMonth(Object date) {
        return currentYearMonth.getYear() == ((LocalDate) date).getYear() &&
                currentYearMonth.getMonth() == ((LocalDate) date).getMonth();
    }

    /**
     * Sets the calendar layout
     *
     * @param tasks The task list from Duke containing all the tasks
     */
    private void setCalendarLayout(TaskListAllTypes tasks) {
        setCalendarBasics(tasks);
        refreshCalendar();
    }

    /**
     * Sets the basic information of the calendar; current month, year and tasks
     */
    private void setCalendarBasics(TaskListAllTypes tasks) {
        ZoneId zoneId = ZoneId.systemDefault(); //GMT +8
        currentYearMonth = YearMonth.now(zoneId).minusMonths(0);
        this.tasks = tasks;
    }

    /**
     * Creates a new CalendarWindow.
     *
     * @param root Stage to use as the root of the CalendarWindow.
     */
    public CalendarWindow(Stage root, TaskListAllTypes tasks) {
        super(FXML, root);
        root.getScene().getStylesheets().addAll(this.getClass().getResource("/css/calendarStyle.css").toExternalForm());
        setCalendarLayout(tasks);
    }

    /**
     * Creates a new CalendarWindow.
     */
    public CalendarWindow(TaskListAllTypes tasks) {
        this(new Stage(), tasks);
    }

    /**
     * Shows the Calendar window.
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show() {
        getRoot().show();
    }

    /**
     * Returns true if the Calendar window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Focuses on the Calendar window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    @FXML
    void previousMonth() {
        currentYearMonth = currentYearMonth.minusMonths(1);
        refreshCalendar();
    }

    @FXML
    void nextMonth() {
        currentYearMonth = currentYearMonth.plusMonths(1);
        refreshCalendar();
    }
}
