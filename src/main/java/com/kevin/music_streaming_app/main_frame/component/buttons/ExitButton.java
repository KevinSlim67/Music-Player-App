package com.kevin.music_streaming_app.main_frame.component.buttons;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;


public class ExitButton extends BorderPane {
    Button exitBtn;
    Color red = new Color(0.95, 0.3, 0.3, 1);
    public ExitButton(EventHandler eventHandler) {
        this.setHeight(20);
        this.setWidth(20);

        exitBtn = new Button("x");
        exitBtn.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;" +
                " -fx-font-size: 15px");
        exitBtn.setTextFill(Color.WHITE);

        exitBtn.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> exitPaneMouseEnter());
        exitBtn.addEventFilter(MouseEvent.MOUSE_EXITED, e -> exitPaneMouseLeave());
        exitBtn.setOnAction(eventHandler);

        this.setCenter(exitBtn);
    }

    private void exitPaneMouseEnter() {
        exitBtn.setTextFill(red);
        this.setStyle("-fx-background-color: rgba(0, 0, 0, 0.11);" +
                " -fx-background-radius: 50px;");
    }

    private void exitPaneMouseLeave() {
        exitBtn.setTextFill(Color.WHITE);
        this.setStyle("-fx-background-color: transparent");
    }
}
