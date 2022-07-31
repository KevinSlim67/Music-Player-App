package com.kevin.music_streaming_app.component;

import com.kevin.music_streaming_app.App;
import com.kevin.music_streaming_app.sections.song_pane.SongPane;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Song extends VBox {

    ImageView imageView;

    public Song() {

        Image image = new Image("song_image.jpg");
        imageView = new ImageView(image);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        roundImage(imageView, 20);

        imageView.setPreserveRatio(true); //preserves aspect ratio
        imageView.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> onClick());
        imageView.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> onEnter());
        imageView.addEventFilter(MouseEvent.MOUSE_EXITED, e -> onLeave());


        Label title = new Label("Song");
        title.setStyle("-fx-font-size: 15px;");

        Label author = new Label("Author");
        author.setStyle("-fx-font-size: 12px");

        VBox.setMargin(title, new Insets(5, 0, 0, 0));
        VBox.setMargin(author, new Insets(2, 0, 0, 0));

        this.getChildren().addAll(imageView, title, author);
    }

    private void onClick() {
        SongPane pane = new SongPane();
        App.getRoot().getChildren().add(pane);
    }

    private void onEnter() {
        imageView.setFitHeight(102);
        imageView.setFitWidth(102);
        App.getScene().setCursor(Cursor.HAND);
    }

    private void onLeave() {
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        App.getScene().setCursor(Cursor.DEFAULT);
    }

    private void roundImage(ImageView imageView, int roundingSize) {
        // set a clip to apply rounded border to the original image.
        Rectangle clip = new Rectangle(
                imageView.getFitWidth(), imageView.getFitHeight()
        );
        clip.setArcWidth(roundingSize);
        clip.setArcHeight(roundingSize);
        imageView.setClip(clip);

        // snapshot the rounded image.
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image = imageView.snapshot(parameters, null);

        // remove the rounding clip so that our effect can show through.
        imageView.setClip(null);

        // store the rounded image in the imageView.
        imageView.setImage(image);
    }
}
