package com.kevin.music_streaming_app.main_frame.component.buttons;

import com.kevin.music_streaming_app.AppStage;
import com.kevin.music_streaming_app.features.ImageStyle;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class CategoryButton extends VBox {

    ImageView imageView;

    public CategoryButton(String category) {
        this.setAlignment(Pos.CENTER);
        this.setSpacing(5);

        Image image = new Image("heart_background.jpg");
        imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        ImageStyle.round(imageView, 20);
        imageView.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> onEnter());
        imageView.addEventFilter(MouseEvent.MOUSE_EXITED, e -> onLeave());

        Label categoryLabel = new Label(category);
        categoryLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");

        this.getChildren().addAll(imageView, categoryLabel);
    }

    private void onEnter() {
        imageView.setScaleX(1.02);
        imageView.setScaleY(1.02);
        AppStage.returnScene().setCursor(Cursor.HAND);
    }

    private void onLeave() {
        imageView.setScaleX(1);
        imageView.setScaleY(1);
        AppStage.returnScene().setCursor(Cursor.DEFAULT);
    }
}
