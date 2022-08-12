package com.kevin.music_streaming_app.main_frame.sections.song_pane;

import com.kevin.music_streaming_app.features.ImageStyle;
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
        ImageStyle.round(imageView, 3);

        centerPane.setCenter(imageView);

        this.setCenter(centerPane);
    }

}
