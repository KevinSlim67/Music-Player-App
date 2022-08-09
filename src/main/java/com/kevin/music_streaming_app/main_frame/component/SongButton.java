package com.kevin.music_streaming_app.main_frame.component;

import com.kevin.music_streaming_app.AppStage;
import com.kevin.music_streaming_app.audio.PausablePlayer;
import com.kevin.music_streaming_app.db.Song;
import com.kevin.music_streaming_app.main_frame.sections.song_pane.SongPane;
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

import java.io.InputStream;
import java.sql.SQLException;

public class SongButton extends VBox {

    ImageView imageView;
    Song song;

    public SongButton(Song song) {
        this.song = song;
        InputStream coverStream = null;
        this.setMaxWidth(100);

        try {
            coverStream = song.getCover().getBinaryStream(); //makes blob data readable
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Image image = new Image(coverStream);
        imageView = new ImageView(image);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        roundImage(imageView, 20);

        imageView.setPreserveRatio(true); //preserves aspect ratio
        imageView.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> onClick());
        imageView.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> onEnter());
        imageView.addEventFilter(MouseEvent.MOUSE_EXITED, e -> onLeave());


        Label title = new Label(song.getName());
        title.setStyle("-fx-font-size: 12px; -fx-font-weight: bold");
        title.setWrapText(true);
        title.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> onClick());

        Label author = new Label(song.getUser());
        author.setStyle("-fx-font-size: 10px");

        VBox.setMargin(title, new Insets(5, 0, 0, 0));
        VBox.setMargin(author, new Insets(2, 0, 0, 0));

        this.getChildren().addAll(imageView, title, author);
    }

    //creates Song Pane and plays the required song
    private void onClick() {
        SongPane pane = new SongPane(song.getCover());
        AppStage.getRoot().getChildren().add(pane);
        AppStage.setPlayer(new PausablePlayer(song.getSong()));
        AppStage.getPlayer().play();
    }

    private void onEnter() {
        imageView.setScaleX(1.02);
        imageView.setScaleY(1.02);
        AppStage.returnScene().setCursor(Cursor.HAND);
    }

    private void onLeave() {
        imageView.setScaleX(1);
        imageView.setScaleY(1);
        AppStage.returnScene().setCursor(Cursor.DEFAULT);
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
