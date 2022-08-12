package com.kevin.music_streaming_app.main_frame.component.buttons;

import com.kevin.music_streaming_app.AppStage;
import com.kevin.music_streaming_app.audio.PausablePlayer;
import com.kevin.music_streaming_app.db.Song;
import com.kevin.music_streaming_app.main_frame.sections.song_pane.SongPane;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PlayButton extends Button {

    private boolean isPlaying = true;

    public PlayButton() {
        Image pause = new Image("file:assets/icons/stop_btn.png");
        ImageView imageView = new ImageView(pause);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(30);
        imageView.setFitWidth(30);

        this.setGraphic(imageView);
        this.setOnAction(e -> onClick());
        this.setStyle("-fx-background-color: transparent");
    }

    private void onClick() {
        Image image;

        if (isPlaying) {
            image = new Image("file:assets/icons/play_btn.png");
            Song.pause();
        } else {
            image = new Image("file:assets/icons/stop_btn.png");
            Song.resume();
        }
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(30);
        imageView.setFitWidth(30);
        imageView.setPreserveRatio(true);

        this.setGraphic(imageView);
        isPlaying = !isPlaying;

    }
}
