package com.kevin.music_streaming_app.main_frame.sections.song_pane;

import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

public class SongPaneCenter extends BorderPane {

    BorderPane centerPane;

    public SongPaneCenter(Blob songCover) {
        centerPane = new BorderPane();

        InputStream imageStream = null;
        try {
            imageStream = songCover.getBinaryStream();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Image image = new Image(imageStream);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(400);
        imageView.setFitHeight(400);
        imageView.setPreserveRatio(true);
        roundImage(imageView, 3);

        centerPane.setCenter(imageView);

        this.setCenter(centerPane);
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
