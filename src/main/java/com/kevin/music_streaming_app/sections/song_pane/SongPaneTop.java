package com.kevin.music_streaming_app.sections.song_pane;

import com.kevin.music_streaming_app.App;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;


public class SongPaneTop extends FlowPane {
    Button exitBtn;
    BorderPane exitBtnPane;
    Color red = new Color(0.95, 0.3, 0.3, 1);

    public SongPaneTop() {
        this.setAlignment(Pos.CENTER_RIGHT);

        exitBtnPane = new BorderPane();

        exitBtn = new Button("x");
        exitBtn.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;" +
                " -fx-font-size: 15px");
        exitBtn.setTextFill(Color.WHITE);

        exitBtn.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> exitPaneMouseEnter());
        exitBtn.addEventFilter(MouseEvent.MOUSE_EXITED, e -> exitPaneMouseLeave());
        exitBtn.setOnAction(e -> App.getRoot().getChildren().remove(1));

        exitBtnPane.setCenter(exitBtn);

        this.getChildren().addAll(exitBtnPane);
    }

    private void exitPaneMouseEnter() {
        exitBtn.setTextFill(red);
        exitBtnPane.setStyle("-fx-background-color: rgba(0, 0, 0, 0.11);" +
                " -fx-background-radius: 50px;");
    }

    private void exitPaneMouseLeave() {
        exitBtn.setTextFill(Color.WHITE);
        exitBtnPane.setStyle("-fx-background-color: transparent");
    }

}
