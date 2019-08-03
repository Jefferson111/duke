package duke.ui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //note the difference between margin and padding, to create the same effect in javafx, the following is used
        this.setStyle("-fx-background-color: lightblue;" + "-fx-background-radius: 20;" + "-fx-padding: 15;" +
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
        this.setStyle("-fx-background-color: lightgreen;" + "-fx-background-radius: 20;" + "-fx-padding: 15px;" +
                "-fx-border-insets: 5px;" +
                "-fx-background-insets: 5px;");
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}