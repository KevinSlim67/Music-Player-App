package com.kevin.music_streaming_app.main_frame.component.buttons;

import com.kevin.music_streaming_app.AppStage;
import com.kevin.music_streaming_app.audio.PausablePlayer;
import com.kevin.music_streaming_app.db.Song;
import com.kevin.music_streaming_app.main_frame.sections.song_pane.SongPane;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class PlayButton extends Button {

    private boolean isPlaying = true;
    private int size;

    public PlayButton(int size) {
        this.size = size;
        Image pause = new Image("file:assets/icons/stop_btn.png");
        ImageView imageView = new ImageView(pause);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(size);
        imageView.setFitWidth(size);

        this.setGraphic(imageView);
        this.setOnAction(e -> onClick());
        this.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> mouseEnter());
        this.addEventFilter(MouseEvent.MOUSE_EXITED, e -> mouseLeave());
        this.setStyle("-fx-background-color: transparent");
    }

    private void onClick() {
        Image image;

        if (isPlaying) {
            image = new Image("file:assets/icons/play_btn_hovered.png");
            Song.pause();
        } else {
            image = new Image("file:assets/icons/stop_btn_hovered.png");
            Song.resume();
        }
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(size);
        imageView.setFitWidth(size);
        imageView.setPreserveRatio(true);

        this.setGraphic(imageView);

        isPlaying = !isPlaying;
    }

    private void mouseEnter() {
        Image image;
        if (isPlaying) image = new Image("file:assets/icons/stop_btn_hovered.png");
        else image = new Image("file:assets/icons/play_btn_hovered.png");
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(size);
        imageView.setFitHeight(size);
        this.setGraphic(imageView);
    }

    private void mouseLeave() {
        Image image;
        if (isPlaying) image = new Image("file:assets/icons/stop_btn.png");
        else image = new Image("file:assets/icons/play_btn.png");
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(size);
        imageView.setFitHeight(size);
        this.setGraphic(imageView);
    }
}
