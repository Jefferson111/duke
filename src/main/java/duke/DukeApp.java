package duke;

import duke.ui.MainWindow;
import duke.ui.Stoppable;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class DukeApp extends Application implements Stoppable {

    @Override
    public void start(Stage stage) throws Exception{
        FXMLLoader loader = new FXMLLoader(
                DukeApp.class.getResource("/view/MainWindow.fxml"));
        stage.setTitle("Duke level-8");
        BorderPane borderPane = (BorderPane) loader.load(); //Exception handling lmao
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.show();

        MainWindow mainWindow = loader.getController();
        mainWindow.initialise(this);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        Platform.exit();
        System.exit(0);
    }

    public static void main(String[] args) {
        launch();
    }

}
