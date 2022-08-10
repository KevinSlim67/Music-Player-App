package com.kevin.music_streaming_app.main_frame.component.buttons;

import com.kevin.music_streaming_app.AppStage;
import com.kevin.music_streaming_app.db.Song;
import com.kevin.music_streaming_app.db.User;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.sql.SQLException;

public class SongButtonV2 extends HBox {
    public SongButtonV2(Song song) {
        this.setHeight(50);
        this.setPrefWidth(500);
        this.setAlignment(Pos.CENTER_LEFT);
        this.getStyleClass().add("horizontal-song-btn");
        this.setStyle("-fx-background-color: #FFFFFF11");
        this.setSpacing(10);

        Image cover = null;
        try {
            cover = new Image(song.getCover().getBinaryStream());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ImageView coverView = new ImageView(cover);
        coverView.setFitWidth(50);
        coverView.setFitHeight(50);
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
        FollowButton followBtn = new FollowButton(AppStage.getUser(),
                User.searchUserByName(song.getUser()), 15);
        artistHBox.getChildren().addAll(followBtn, artistName);

        songInfoPane.getChildren().addAll(songHBox, artistHBox);




        this.getChildren().addAll(coverView, songInfoPane);
    }
}
