package com.kevin.music_streaming_app.sections.song_pane;

import com.kevin.music_streaming_app.App;
import com.kevin.music_streaming_app.audio.AudioPlayer;
import com.kevin.music_streaming_app.db.Song;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class SongPaneBottom extends BorderPane {
    ImageView playBtnView;
    boolean isPlay = true;

    public SongPaneBottom() {
        this.setPrefHeight(60);
        HBox center = new HBox(10);
        center.setAlignment(Pos.CENTER);
        Image playBtn = new Image("file:assets/icons/stop_btn.png");
        playBtnView = new ImageView(playBtn);
        playBtnView.setFitHeight(25);
        playBtnView.setFitWidth(25);
        playBtnView.setPreserveRatio(true);
        playBtnView.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> playBtnOnClick());

        Button pauseBtn = new Button("Pause");
        Button plaBtn = new Button("Play");

        pauseBtn.setOnAction(e -> Song.pause());
        plaBtn.setOnAction(e -> Song.resume());

        center.getChildren().addAll(pauseBtn, plaBtn, playBtnView);

        this.setCenter(center);
    }

    private void playBtnOnClick() {
        System.out.println("Clicked - " + isPlay);
        if (isPlay) {
            playBtnView.setImage(new Image("file:assets/icons/play_btn.png"));
        } else {
            playBtnView.setImage(new Image("file:assets/icons/stop_btn.png"));
        }
        isPlay = !isPlay;
    }
}
