package storage;

import commons.DukeException;
import commons.Message;
import parsers.ParserStorage;
import tasks.Task;

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

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads tasks from filepath.
     *
     * @return List of tasks.
     * @throws DukeException If tasks.txt cannot be converted to task.
     * @throws FileNotFoundException If file not found.
     */
    public ArrayList<Task> read() throws DukeException, FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        ArrayList<Task> tasks = new ArrayList<>();
        while (s.hasNext()) {
            tasks.add(ParserStorage.createTaskFromStorage(s.nextLine()));
        }
        return tasks;
    }

    /**
     * Writes the tasks into a file of the given filepath.
     *
     * @param tasks List of tasks.
     * @throws IOException If file cannot be written/found.
     * @throws DukeException If task cannot be converted to String in tasks.txt.
     */
    public void write(ArrayList<Task> tasks) throws IOException, DukeException {
        FileWriter writer = new FileWriter(filePath);
        for (Task task : tasks) {
            writer.write(ParserStorage.toStorageString(task) + "\n");
        }
        writer.close();
    }
}
