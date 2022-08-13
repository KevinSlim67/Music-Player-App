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

public class UserButtonV2 extends BorderPane {
    User user;

    public UserButtonV2(User user, int width, int height) {
        this.user = user;

        this.setPrefHeight(height);
        this.setPrefWidth(width);
        this.getStyleClass().add("horizontal-song-btn");
        this.setStyle("-fx-background-color: #FFFFFF11");

//        Image cover = null;
//        ImageView coverView = new ImageView(cover);
//        coverView.setFitWidth(height);
//        coverView.setFitHeight(height);
//        coverView.setPreserveRatio(true);

        HBox artistHBox = new HBox(5);
        artistHBox.setAlignment(Pos.CENTER_LEFT);
        Label artistName = new Label(user.getName());
        artistName.setStyle("-fx-font-size: 12px");
        FollowButton followBtn = new FollowButton(user.getId(), 15);
        artistHBox.getChildren().addAll(followBtn, artistName);


        BorderPane.setMargin(artistHBox, new Insets(0, 0, 0, 10));

//        this.setLeft(coverView);
        this.setCenter(artistHBox);
    }

}
