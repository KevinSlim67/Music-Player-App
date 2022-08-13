package com.kevin.music_streaming_app.main_frame.layout;

import com.kevin.music_streaming_app.AppStage;
import com.kevin.music_streaming_app.LoginStage;
import com.kevin.music_streaming_app.StageManager;
import com.kevin.music_streaming_app.audio.PausablePlayer;
import com.kevin.music_streaming_app.db.Song;
import com.kevin.music_streaming_app.db.User;
import com.kevin.music_streaming_app.main_frame.component.buttons.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.SQLException;

public class SongBar extends BorderPane {
    Song song;

    public SongBar(Song song) {
        this.song = song;
        this.prefHeight(70);
        this.setStyle("-fx-background-color: #020429;");

        Image cover = null;
        try {
            cover = new Image(song.getCover().getBinaryStream());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ImageView coverView = new ImageView(cover);
        coverView.setFitWidth(70);
        coverView.setFitHeight(70);
        coverView.setPreserveRatio(true);

        VBox songInfoPane = new VBox(2);

        songInfoPane.setAlignment(Pos.CENTER_LEFT);
        HBox songHBox = new HBox(5);
        songHBox.setAlignment(Pos.CENTER_LEFT);

        Label songName = new Label(song.getName());
        songName.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        LikeButton likeBtn = new LikeButton(AppStage.getUser(),
                song.getId(), 20);
        songHBox.getChildren().addAll(likeBtn, songName);

        HBox artistHBox = new HBox(5);
        artistHBox.setAlignment(Pos.CENTER_LEFT);
        Label artistName = new Label(song.getUser());
        artistName.setStyle("-fx-font-size: 14px");
        FollowButton followBtn = new FollowButton(User.searchUserByName(song.getUser()).getId(), 20);
        artistHBox.getChildren().addAll(followBtn, artistName);

        songInfoPane.getChildren().addAll(songHBox, artistHBox);

        HBox rightPane = new HBox(1);
        rightPane.setAlignment(Pos.CENTER);

        ExpandButton expandButton = new ExpandButton(song, 26);
        PlayButton playButton = new PlayButton(30);
        ExitButton exitButton = new ExitButton(e -> exitClick(),30);

        rightPane.getChildren().addAll(expandButton, playButton, exitButton);

        BorderPane.setMargin(songInfoPane, new Insets(0, 0, 0, 10));
        BorderPane.setMargin(rightPane, new Insets(0, 20, 0, 0));

        this.setLeft(coverView);
        this.setCenter(songInfoPane);
        this.setRight(rightPane);
    }

    private void exitClick() {
        PausablePlayer player = AppStage.getPlayer();
        if (player != null) player.stop(); //stops player
        AppStage.getThreads().clear(); //clears all previous threads
        AppStage.getBorderPane().setBottom(null); //removes SongBar
    }
}
