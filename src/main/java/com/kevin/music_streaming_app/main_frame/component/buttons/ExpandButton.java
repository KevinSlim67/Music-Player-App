package com.kevin.music_streaming_app.main_frame.component.buttons;

import com.kevin.music_streaming_app.AppStage;
import com.kevin.music_streaming_app.audio.PausablePlayer;
import com.kevin.music_streaming_app.db.RecentlyListened;
import com.kevin.music_streaming_app.db.Song;
import com.kevin.music_streaming_app.main_frame.layout.SongBar;
import com.kevin.music_streaming_app.main_frame.sections.song_pane.SongPane;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ExpandButton extends Button {

    private Song song;
    private int size;

    public ExpandButton(Song song, int size) {
        this.song = song;
        this.size = size;
        Image pause = new Image("file:assets/icons/expand.png");
        ImageView imageView = new ImageView(pause);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(size);
        imageView.setFitWidth(size);

        this.setGraphic(imageView);
        this.setOnAction(e -> onClick());
        this.setStyle("-fx-background-color: transparent");
        this.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> mouseEnter());
        this.addEventFilter(MouseEvent.MOUSE_EXITED, e -> mouseLeave());
    }

    //creates Song Pane and plays the required song
    private void onClick() {
        SongPane pane = new SongPane(song);
        AppStage.getRoot().getChildren().add(pane);
        AppStage.getBorderPane().setBottom(null);
    }

    private void mouseEnter() {
        Image image = new Image("file:assets/icons/expand_hovered.png");
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(size);
        imageView.setFitHeight(size);
        this.setGraphic(imageView);
    }

    private void mouseLeave() {
        Image image = new Image("file:assets/icons/expand.png");
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(size);
        imageView.setFitHeight(size);
        this.setGraphic(imageView);
    }
}
