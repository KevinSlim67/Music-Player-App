package com.kevin.music_streaming_app.main_frame.component.buttons;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;


public class ExitButton extends BorderPane {
    private Button exitBtn = new Button();;
    private Color red = new Color(0.95, 0.3, 0.3, 1);
    private int size;
    public ExitButton(EventHandler eventHandler, int size) {
        this.size = size;


        Image exitImage = new Image("file:assets/icons/exit.png");
        ImageView exitImageView = new ImageView(exitImage);
        exitImageView.setPreserveRatio(true);
        exitImageView.setFitWidth(size);
        exitImageView.setFitHeight(size);
        exitBtn.setGraphic(exitImageView);
        exitBtn.setStyle("-fx-background-color: transparent;");

        exitBtn.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> exitPaneMouseEnter());
        exitBtn.addEventFilter(MouseEvent.MOUSE_EXITED, e -> exitPaneMouseLeave());
        exitBtn.setOnAction(eventHandler);

        this.setCenter(exitBtn);
    }

    private void exitPaneMouseEnter() {
        Image exitImage = new Image("file:assets/icons/exit_hovered.png");
        ImageView exitImageView = new ImageView(exitImage);
        exitImageView.setPreserveRatio(true);
        exitImageView.setFitWidth(size);
        exitImageView.setFitHeight(size);
        exitBtn.setGraphic(exitImageView);
    }

    private void exitPaneMouseLeave() {
        Image exitImage = new Image("file:assets/icons/exit.png");
        ImageView exitImageView = new ImageView(exitImage);
        exitImageView.setPreserveRatio(true);
        exitImageView.setFitWidth(size);
        exitImageView.setFitHeight(size);
        exitBtn.setGraphic(exitImageView);
    }
}
