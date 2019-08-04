package duke.ui;

import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends UiPart<HBox> {

    private static final String FXML = "DialogBox.fxml";

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        super(FXML);
        //note the difference between margin and padding, to create the same effect in javafx, the following is used
        this.getRoot().setStyle("-fx-background-color: lightblue;" + "-fx-background-radius: 20;" + "-fx-padding: 15;" +
                "-fx-border-insets: 5;" +
                "-fx-background-insets: 5;");
        dialog.setText(text);
        dialog.setMinHeight(Label.USE_PREF_SIZE); //prevent text from truncating to "..."
        displayPicture.setImage(img);
        //roundImageView(); //**note change to circle/imagepattern instead of imageview
    }

    private void roundImageView() {
        final int radius = 30;
        final Circle clip = new Circle(displayPicture.getX() + radius, displayPicture.getY() + radius, radius);
        displayPicture.setClip(clip);

        /*
        Circle circle = new Circle(radius);
        ImagePattern pattern = new ImagePattern(myImage);
        circle.setFill(pattern);

        circle.setEffect(new DropShadow(8, Color.rgb(0, 0, 0, 0.8)));
        */

    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.getRoot().setStyle("-fx-background-color: lightgreen;" + "-fx-background-radius: 20;" + "-fx-padding: 15px;" +
                "-fx-border-insets: 5px;" +
                "-fx-background-insets: 5px;");
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getRoot().getChildren());
        Collections.reverse(tmp);
        this.getRoot().getChildren().setAll(tmp);
        this.getRoot().setAlignment(Pos.TOP_LEFT);
    }

    public static HBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img).getRoot();
    }

    public static HBox getDukeDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.flip();
        return db.getRoot();
    }
}