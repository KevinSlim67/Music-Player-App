package com.kevin.music_streaming_app.main_frame.sections.song_pane;

import com.kevin.music_streaming_app.db.Song;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class SongPaneBottom extends BorderPane {
    ImageView playBtnView;
    boolean isPlay = true;

    public SongPaneBottom() {
        this.setPrefHeight(60);
        this.setPadding(new Insets(10, 10, 10, 10));

        HBox leftPane = new HBox(10);
        HBox rightPane = new HBox(10);

        HBox topPane = new HBox(10);
        topPane.setAlignment(Pos.CENTER);

        Button pauseBtn = new Button("Pause");
        Button playBtn = new Button("Play");
        pauseBtn.setOnAction(e -> Song.pause());
        playBtn.setOnAction(e -> Song.resume());
        leftPane.getChildren().addAll(pauseBtn, playBtn);



//        ProgressionSlider progressionSlider = new ProgressionSlider();
//        topPane.getChildren().add(progressionSlider);

        SoundSlider soundSlider = new SoundSlider();
        rightPane.getChildren().add(soundSlider);

        this.setTop(topPane);
        this.setRight(rightPane);
        this.setLeft(leftPane);
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
