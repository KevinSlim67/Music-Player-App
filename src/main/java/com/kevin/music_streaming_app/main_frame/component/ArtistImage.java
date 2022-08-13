package com.kevin.music_streaming_app.main_frame.component;

import com.kevin.music_streaming_app.AppStage;
import com.kevin.music_streaming_app.db.User;
import com.kevin.music_streaming_app.features.ImageStyle;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.sql.SQLException;

public class ArtistImage extends VBox {
    private User user;
    private int size;
    private Image image = new Image("file:assets/icons/gray_person.png");
    private StackPane imagePane;
    private ImageView imageView;

    public ArtistImage(User user, int size, boolean clickable) {
        this.user = user;
        this.size = size;

        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);


        if (user.getProfilePicture() != null) {
            try {
                image = new Image(user.getProfilePicture().getBinaryStream());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        imagePane = new StackPane();
        imagePane.setStyle("-fx-background-color: #666666; -fx-background-radius: 9000px");
        imagePane.setPrefWidth(size);
        imagePane.setPrefHeight(size);

        if (clickable) {
            imagePane.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> onEnter());
            imagePane.addEventFilter(MouseEvent.MOUSE_EXITED, e -> onLeave());
        }

        imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(size);
        imageView.setFitWidth(size);
        imagePane.getChildren().add(imageView);
        ImageStyle.round(imageView, 5000);

        Label artistName = new Label(user.getName());
        artistName.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        this.getChildren().addAll(imagePane, artistName);
    }

    private void onEnter() {
        imagePane.setScaleX(1.02);
        imagePane.setScaleY(1.02);
        AppStage.returnScene().setCursor(Cursor.HAND);
    }

    private void onLeave() {
        imagePane.setScaleX(1);
        imagePane.setScaleY(1);
        AppStage.returnScene().setCursor(Cursor.DEFAULT);
    }
}
