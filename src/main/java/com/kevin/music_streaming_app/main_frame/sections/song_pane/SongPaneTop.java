package com.kevin.music_streaming_app.main_frame.sections.song_pane;

import com.kevin.music_streaming_app.AppStage;
import com.kevin.music_streaming_app.main_frame.component.buttons.ExitButton;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;


public class SongPaneTop extends FlowPane {
    public SongPaneTop() {
        this.setAlignment(Pos.CENTER_RIGHT);

        ExitButton exitButton = new ExitButton(e ->AppStage.getRoot().getChildren().remove(1));

        this.getChildren().addAll(exitButton);
    }

}
