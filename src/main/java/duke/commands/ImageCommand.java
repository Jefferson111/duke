package duke.commands;

import duke.commons.DukeException;
import duke.data.taskList.TaskListAllTypes;
import duke.logic.Classificator;
import duke.logic.ImageProcessor;
import duke.storage.Storage;
import duke.ui.Ui;

import java.io.File;
import java.util.List;

public class ImageCommand extends Command {
    private Classificator classificator;
    private float[][][][] imageData;

    public ImageCommand(File imageFile, Classificator classificator) throws DukeException {
        try {
            imageData = ImageProcessor.loadAndNormalizeImages(imageFile.getAbsolutePath());
            this.classificator = classificator;
        } catch (Exception e) {
            throw new DukeException("Error in converting Image" + e.getMessage());
        }
    }

    @Override
    public void execute(TaskListAllTypes tasks, Ui ui, Storage storage) throws DukeException {
        List<String> result = classificator.classify(imageData);
        ui.show(result.get(0));
    }
}
