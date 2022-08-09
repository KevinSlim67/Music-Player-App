package com.kevin.music_streaming_app.main_frame.sections.song_pane;

import com.kevin.music_streaming_app.db.Song;
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
    private Button likedBtn = new Button(), playBtn = new Button(), followedBtn = new Button();
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

        Image pause = new Image("file:assets/icons/stop_btn.png");
        ImageView imageView = new ImageView(pause);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(30);
        imageView.setFitWidth(30);

        playBtn.setGraphic(imageView);
        playBtn.setOnAction(e -> playBtnOnClick());
        playBtn.setStyle("-fx-background-color: transparent");

        centerPane.getChildren().add(playBtn);
        this.setCenter(centerPane);
    }

    private void createLeftPane() {
        leftPane.setPrefWidth(200);
        leftPane.setMaxWidth(200);
        leftPane.setPadding(new Insets(0, 0, 0, 2));

        HBox nameBox = new HBox(5);
        nameBox.setAlignment(Pos.CENTER_LEFT);

        Image likedImg = new Image("file:assets/icons/like.png");
        ImageView likedImgView = new ImageView(likedImg);
        likedImgView.setFitWidth(20);
        likedImgView.setFitHeight(20);

        likedBtn.setPadding(new Insets(0));
        likedBtn.setStyle("-fx-background-color: transparent;");
        likedBtn.setGraphic(likedImgView);
        likedBtn.setOnAction(e -> likeClick());

        Label name = new Label(songName);
        name.setStyle("-fx-font-size: 18px; -fx-font-weight: bold");
        name.setWrapText(true);

        nameBox.getChildren().addAll(likedBtn, name);

        Image followedImg = new Image("file:assets/icons/follow.png");
        ImageView followedImgView = new ImageView(followedImg);
        followedImgView.setFitWidth(20);
        followedImgView.setFitHeight(20);

        followedBtn.setPadding(new Insets(0));
        followedBtn.setStyle("-fx-background-color: transparent;");
        followedBtn.setGraphic(followedImgView);
        followedBtn.setOnAction(e -> followClick());

        HBox singerBox = new HBox(5);
        singerBox.setAlignment(Pos.CENTER_LEFT);

        Label singer = new Label(artist);
        singer.setStyle("-fx-font-size: 14px;");

        singerBox.getChildren().addAll(followedBtn, singer);

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

    private void playBtnOnClick() {
        Image image;
        if (isPlay) {
            image = new Image("file:assets/icons/play_btn.png");
            Song.pause();
        } else {
            image = new Image("file:assets/icons/stop_btn.png");
            Song.resume();
        }
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(30);
        imageView.setFitWidth(30);
        imageView.setPreserveRatio(true);
        playBtn.setGraphic(imageView);
        isPlay = !isPlay;
    }

    private void likeClick() {
        Image image;
        ImageView imageView;
        if (!isLiked) image = new Image("file:assets/icons/like_clicked.png");
        else image = new Image("file:assets/icons/like.png");


        isLiked = !isLiked;
        imageView = new ImageView(image);
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
        likedBtn.setGraphic(imageView);
    }

    private void followClick() {
        Image image;
        ImageView imageView;
        if (!isFollowed) image = new Image("file:assets/icons/follow_clicked.png");
        else image = new Image("file:assets/icons/follow.png");

        isFollowed = !isFollowed;
        imageView = new ImageView(image);
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
        followedBtn.setGraphic(imageView);
    }
}
