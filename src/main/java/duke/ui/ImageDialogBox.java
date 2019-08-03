package duke.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class ImageDialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private ImageDialogBox(Image queryImage, Image profileImage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setStyle("-fx-background-color: yellow;" + "-fx-background-radius: 20;" + "-fx-padding: 15;" +
                "-fx-border-insets: 5;" +
                "-fx-background-insets: 5;");
        dialog.setText("<-----------------Image<-----------------Query");
        dialog.setMinHeight(Label.USE_PREF_SIZE); //prevent text from truncating to "..."
        displayPicture.setImage(profileImage);
        insertImage(queryImage);
    }

    private void insertImage(Image queryImage) {
        ImageView iv = new ImageView(queryImage);
        iv.setFitHeight(99);
        iv.setFitWidth(99);
        iv.setPreserveRatio(true);
        iv.setPickOnBounds(true);
        this.getChildren().add(0, iv);
    }

    public static ImageDialogBox getUserDialog(Image queryImage, Image profileImage) {
        return new ImageDialogBox(queryImage, profileImage);
    }
}
