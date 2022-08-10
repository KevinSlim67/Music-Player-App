package com.kevin.music_streaming_app.main_frame.component.buttons;

import com.kevin.music_streaming_app.db.LikedSong;
import com.kevin.music_streaming_app.db.User;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LikeButton extends Button {
    private boolean isLiked = false;
    private int size;
    private User currentUser;
    private int songId;
    LikedSong likedSong;

    public LikeButton(User currentUser, int songId, int size) {
        likedSong = new LikedSong(songId, currentUser.getId());
        Image likedImg = new Image("file:assets/icons/like.png");


        if (likedSong.exists()) {
            likedImg = new Image("file:assets/icons/like_clicked.png");
            isLiked = true;
        }

        this.size = size;
        this.currentUser = currentUser;
        this.songId = songId;


        ImageView likedImgView = new ImageView(likedImg);
        likedImgView.setFitWidth(size);
        likedImgView.setFitHeight(size);

        this.setPadding(new Insets(0));
        this.setStyle("-fx-background-color: transparent;");
        this.setGraphic(likedImgView);
        this.setOnAction(e -> likeClick());
    }

    private void likeClick() {
        Image image;
        ImageView imageView;
        if (!isLiked) {
            image = new Image("file:assets/icons/like_clicked.png");
            likedSong.insert();
        } else {
            image = new Image("file:assets/icons/like.png");
            likedSong.delete();
        }

        isLiked = !isLiked;
        imageView = new ImageView(image);
        imageView.setFitWidth(size);
        imageView.setFitHeight(size);
        this.setGraphic(imageView);
    }
}
