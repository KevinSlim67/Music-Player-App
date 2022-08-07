package com.kevin.music_streaming_app.main_frame.sections.song_pane;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

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

        centerPane.setCenter(imageView);

        this.setCenter(centerPane);
    }
}
