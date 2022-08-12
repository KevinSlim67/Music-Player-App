package com.kevin.music_streaming_app.main_frame.sections.song_pane;

import com.kevin.music_streaming_app.AppStage;
import com.kevin.music_streaming_app.db.Song;
import com.kevin.music_streaming_app.db.User;
import com.kevin.music_streaming_app.main_frame.component.buttons.FollowButton;
import com.kevin.music_streaming_app.main_frame.component.buttons.LikeButton;
import com.kevin.music_streaming_app.main_frame.component.buttons.PlayButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class SongPaneBottom extends BorderPane {
    private boolean isPlay = true, isLiked = false, isFollowed = false;
    private Button playBtn = new Button();
    private VBox leftPane = new VBox(2);
    private HBox rightPane = new HBox(10);
    private HBox centerPane = new HBox(10);
    private String songName, artist;


    public SongPaneBottom(String songName, String artist) {
        this.songName = songName;
        this.artist = artist;

        this.setPrefHeight(60);
        this.setPadding(new Insets(10));

        createLeftPane();
        createCenterPane();
        createRightPane();

    }

    private void createCenterPane() {
        centerPane.setPrefWidth(200);
        centerPane.setAlignment(Pos.CENTER);

        PlayButton playBtn = new PlayButton();

        centerPane.getChildren().add(playBtn);
        this.setCenter(centerPane);
    }

    private void createLeftPane() {
        leftPane.setPrefWidth(200);
        leftPane.setMaxWidth(200);
        leftPane.setPadding(new Insets(0, 0, 0, 2));

        HBox nameBox = new HBox(5);
        nameBox.setAlignment(Pos.CENTER_LEFT);

        LikeButton likeBtn = new LikeButton(AppStage.getUser(),
                Song.searchId(songName), 20);

        Label name = new Label(songName);
        name.setStyle("-fx-font-size: 18px; -fx-font-weight: bold");
        name.setWrapText(true);

        nameBox.getChildren().addAll(likeBtn, name);

        Image followedImg = new Image("file:assets/icons/follow.png");
        ImageView followedImgView = new ImageView(followedImg);
        followedImgView.setFitWidth(20);
        followedImgView.setFitHeight(20);

        FollowButton followBtn = new FollowButton(AppStage.getUser(),
                User.searchUserByName(artist), 20);

        HBox singerBox = new HBox(5);
        singerBox.setAlignment(Pos.CENTER_LEFT);

        Label singer = new Label(artist);
        singer.setStyle("-fx-font-size: 14px;");

        singerBox.getChildren().addAll(followBtn, singer);

        leftPane.getChildren().addAll(nameBox, singerBox);

        this.setLeft(leftPane);
    }

    private void createRightPane() {
        rightPane.setPrefWidth(200);
        rightPane.setMaxWidth(200);
        rightPane.setAlignment(Pos.CENTER_RIGHT);

        SoundSlider soundSlider = new SoundSlider();

        rightPane.getChildren().addAll(soundSlider);

        this.setRight(rightPane);
    }


}
