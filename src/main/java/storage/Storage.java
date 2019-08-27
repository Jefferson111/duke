package storage;

import commons.DukeException;
import commons.Message;
import parsers.ParserStorage;
import tasks.Task;
import ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages storage of Duke data in local storage.
 */
public class Storage {
    private String filePath;
    private ArrayList<Task> tasks;
    private Ui ui;

    /**
     * Constructs a Storage object that contains tasks and storage related operations.
     * Mainly save tasks and get tasks.
     *
     * @param filePath The filepath to the txt file.
     * @param ui The user interface displaying events on the task list.
     */
    public Storage(String filePath, Ui ui) {
        this.filePath = filePath;
        this.ui = ui;
        read();
    }

    /**
     * Reads tasks from filepath. Creates empty tasks if file cannot be read.
     */
    private void read() {
        ArrayList<Task> newTasks = new ArrayList<>();
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                newTasks.add(ParserStorage.createTaskFromStorage(s.nextLine()));
            }
            s.close();
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        } catch (FileNotFoundException e) {
            ui.showError(Message.FILE_NOT_FOUND);
        }
        tasks = newTasks;
    }

    /**
     * Writes the tasks into a file of the given filepath.
     */
    public void write() {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks) {
                writer.write(ParserStorage.toStorageString(task) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            ui.showError(Message.FILE_NOT_SAVE);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
