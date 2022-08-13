package com.kevin.music_streaming_app.main_frame.component.buttons;

import com.kevin.music_streaming_app.AppStage;
import com.kevin.music_streaming_app.LoginStage;
import com.kevin.music_streaming_app.StageManager;
import com.kevin.music_streaming_app.audio.PausablePlayer;
import com.kevin.music_streaming_app.db.Song;
import com.kevin.music_streaming_app.db.User;
import com.kevin.music_streaming_app.features.Visibility;
import com.kevin.music_streaming_app.main_frame.sections.song_pane.SongPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.sql.SQLException;

public class SongButtonV2 extends BorderPane {
    Song song;

    public SongButtonV2(Song song, int width, int height) {
        this.song = song;

        this.setHeight(height);
        this.setPrefWidth(width);
        this.getStyleClass().add("horizontal-song-btn");
        this.setStyle("-fx-background-color: #FFFFFF11");

        Image cover = null;
        try {
            cover = new Image(song.getCover().getBinaryStream());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ImageView coverView = new ImageView(cover);
        coverView.setFitWidth(height);
        coverView.setFitHeight(height);
        coverView.setPreserveRatio(true);

        VBox songInfoPane = new VBox(2);

        songInfoPane.setAlignment(Pos.CENTER_LEFT);
        HBox songHBox = new HBox(5);
        songHBox.setAlignment(Pos.CENTER_LEFT);

        Label songName = new Label(song.getName());
        songName.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        LikeButton likeBtn = new LikeButton(AppStage.getUser(),
                song.getId(), 18);
        songHBox.getChildren().addAll(likeBtn, songName);

        HBox artistHBox = new HBox(5);
        artistHBox.setAlignment(Pos.CENTER_LEFT);
        Label artistName = new Label(song.getUser());
        artistName.setStyle("-fx-font-size: 12px");
        FollowButton followBtn = new FollowButton(User.searchUserByName(song.getUser()).getId(), 15);
        artistHBox.getChildren().addAll(followBtn, artistName);

        songInfoPane.getChildren().addAll(songHBox, artistHBox);

        HBox rightPane = new HBox(1);
        rightPane.setAlignment(Pos.CENTER);

        StartButton startButton = new StartButton(song, 30);

        rightPane.getChildren().addAll(startButton);

        BorderPane.setMargin(songInfoPane, new Insets(0, 0, 0, 10));

        this.setLeft(coverView);
        this.setCenter(songInfoPane);
        this.setRight(rightPane);
    }

}
