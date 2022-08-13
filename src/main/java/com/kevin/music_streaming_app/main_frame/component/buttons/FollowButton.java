package com.kevin.music_streaming_app.main_frame.component.buttons;

import com.kevin.music_streaming_app.AppStage;
import com.kevin.music_streaming_app.db.User;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FollowButton extends Button {
    private boolean isFollowed = false;
    private int size;
    private int currentUserId = AppStage.getUser().getId();
    private int followedUserId;

    public FollowButton(int followedUserId, int size) {
        this.followedUserId = followedUserId;
        this.size = size;
        Image likedImg = new Image("file:assets/icons/follow.png");
        ImageView likedImgView = new ImageView(likedImg);
        likedImgView.setFitWidth(size);
        likedImgView.setFitHeight(size);

        this.setPadding(new Insets(0));
        this.setStyle("-fx-background-color: transparent;");
        this.setGraphic(likedImgView);
        this.setOnAction(e -> followClick());
    }

    private void followClick() {
        Image image;
        ImageView imageView;
        if (!isFollowed) image = new Image("file:assets/icons/follow_clicked.png");
        else image = new Image("file:assets/icons/follow.png");

        isFollowed = !isFollowed;
        imageView = new ImageView(image);
        imageView.setFitWidth(size);
        imageView.setFitHeight(size);
        this.setGraphic(imageView);
    }
}
