package com.kevin.music_streaming_app.main_frame.component.buttons;

import com.kevin.music_streaming_app.AppStage;
import com.kevin.music_streaming_app.features.ImageStyle;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class CategoryButton extends VBox {

    ImageView imageView;

    public CategoryButton(String category, Image image) {
        this.setAlignment(Pos.CENTER);
        this.setSpacing(5);

        StackPane stackPane = new StackPane();

        Image categoryImage = image;
        imageView = new ImageView(categoryImage);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(90);
        imageView.setFitWidth(90);

        Label categoryLabel = new Label(category);
        categoryLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");

        stackPane.setStyle("-fx-background-color: #666666; -fx-background-radius: 20px");
        stackPane.setPrefHeight(120);
        stackPane.setPrefWidth(120);
        stackPane.getChildren().add(imageView);
        stackPane.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> onEnter());
        stackPane.addEventFilter(MouseEvent.MOUSE_EXITED, e -> onLeave());

        this.getChildren().addAll(stackPane, categoryLabel);
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
