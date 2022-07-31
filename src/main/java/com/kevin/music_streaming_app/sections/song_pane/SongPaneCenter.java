package com.kevin.music_streaming_app.sections.song_pane;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class SongPaneCenter extends BorderPane {

    BorderPane centerPane;

    public SongPaneCenter() {
        centerPane = new BorderPane();

        Image image = new Image("file:assets/song_image.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(400);
        imageView.setFitHeight(400);
        imageView.setPreserveRatio(true);

        centerPane.setCenter(imageView);

        this.setCenter(centerPane);
    }
}
