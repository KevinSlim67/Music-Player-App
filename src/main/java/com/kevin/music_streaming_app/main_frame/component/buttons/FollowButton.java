package com.kevin.music_streaming_app.main_frame.component.buttons;

import com.kevin.music_streaming_app.AppStage;
import com.kevin.music_streaming_app.db.FollowedUser;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FollowButton extends Button {
    private boolean isFollowed;
    private int size;
    private int currentUserId = AppStage.getUser().getId();
    private int followedUserId;
    private FollowedUser followedUser;

    public FollowButton(int followedUserId, int size) {
        this.followedUserId = followedUserId;
        this.size = size;

        followedUser = new FollowedUser(currentUserId, followedUserId);
        Image followedImg = new Image("file:assets/icons/follow.png");

        if (followedUser.exists()) {
            followedImg = new Image("file:assets/icons/follow_clicked.png");
            isFollowed = true;
        }

        ImageView followedImgView = new ImageView(followedImg);
        followedImgView.setFitWidth(size);
        followedImgView.setFitHeight(size);

        this.setPadding(new Insets(0));
        this.setStyle("-fx-background-color: transparent;");
        this.setGraphic(followedImgView);
        this.setOnAction(e -> followClick());
    }

    private void followClick() {
        Image image;
        ImageView imageView;
        if (!isFollowed) {
            image = new Image("file:assets/icons/follow_clicked.png");
            followedUser.insert();
        } else {
            image = new Image("file:assets/icons/follow.png");
            followedUser.delete();
        }

        isFollowed = !isFollowed;
        imageView = new ImageView(image);
        imageView.setFitWidth(size);
        imageView.setFitHeight(size);
        this.setGraphic(imageView);
    }
}
