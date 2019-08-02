package duke.storage;

import duke.data.task.*;
import duke.parser.Parser;
import duke.commons.DukeException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws DukeException {
        List<Task> loadedTasks = new ArrayList<>();
        List<String> lines;
        try {
            lines = getLines(filePath);
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found: " + e.getMessage());
        }
        for (String line : lines) {
            if (line.trim().isEmpty()) { //ignore empty lines
                continue;
            }
            loadedTasks.add(Parser.createTaskFromStorageLine(line)); //convert the line to a task and add to the list
        }

        return loadedTasks;
    }

    public void save(List<Task> tasks) throws DukeException {
        File f = new File(filePath);
        try {
            FileWriter fileWriter = new FileWriter(f);
            for (Task t : tasks) {
                String line;
                if (t instanceof Deadline) {
                    line = "D | " + ((Deadline) t).isDone() + " | " + t.getDescription() + " | " + ((Deadline) t).getBy();
                } else if (t instanceof Todo) {
                    line = "T | " + ((Todo) t).isDone() + " | " + t.getDescription();
                } else if (t instanceof Weekly) {
                    line = "W | " + ((Weekly) t).isDone() + " | " + t.getDescription() + " | " + ((Weekly) t).getOn();
                } else {
                    line = "E | " + ((Event) t).isDone() + " | " + t.getDescription() + " | " + ((Event) t).getAt();
                }
                fileWriter.write(line + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("File not found: " + e.getMessage());
        }
    }

    private static List<String> getLines(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        List<String> lines = new ArrayList<>();
        while (s.hasNext()) {
            lines.add(s.nextLine());
        }
        return lines;
    }
}
