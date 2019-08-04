package duke.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class ImageDialogBox extends UiPart<HBox> {

    private static final String FXML = "DialogBox.fxml";

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private ImageDialogBox(Image queryImage, Image profileImage) {
        super(FXML);

        this.getRoot().setStyle("-fx-background-color: yellow;" + "-fx-background-radius: 20;" + "-fx-padding: 15;" +
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
        this.getRoot().getChildren().add(0, iv);
    }

    public static HBox getUserDialog(Image queryImage, Image profileImage) {
        return new ImageDialogBox(queryImage, profileImage).getRoot();
    }
}
