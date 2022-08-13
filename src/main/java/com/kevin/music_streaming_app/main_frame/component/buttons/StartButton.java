package com.kevin.music_streaming_app.main_frame.component.buttons;

import com.kevin.music_streaming_app.AppStage;
import com.kevin.music_streaming_app.audio.PausablePlayer;
import com.kevin.music_streaming_app.db.RecentlyListened;
import com.kevin.music_streaming_app.db.Song;
import com.kevin.music_streaming_app.main_frame.layout.SongBar;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class StartButton extends Button {

    private Song song;
    private int size;

    public StartButton(Song song, int size) {
        this.song = song;
        this.size = size;
        Image pause = new Image("file:assets/icons/play_btn.png");
        ImageView imageView = new ImageView(pause);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(size);
        imageView.setFitWidth(size);

        this.setGraphic(imageView);
        this.setOnAction(e -> onClick());
        this.setStyle("-fx-background-color: transparent");
    }

    //creates Song Pane and plays the required song
    private void onClick() {
        AppStage.getBorderPane().setBottom(new SongBar(song));
        AppStage.setPlayer(new PausablePlayer(song.getSong()));

        RecentlyListened recentlyListened = new RecentlyListened(AppStage.getUser().getId(), song.getId());
        recentlyListened.delete(); //removes the song if it exists so we don't have duplicates
        recentlyListened.insert(); //adds the song at the bottom of the list

        AppStage.getPlayer().play();
    }
}
