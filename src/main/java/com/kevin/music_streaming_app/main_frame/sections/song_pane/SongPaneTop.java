package com.kevin.music_streaming_app.main_frame.sections.song_pane;

import com.kevin.music_streaming_app.AppStage;
import com.kevin.music_streaming_app.db.Song;
import com.kevin.music_streaming_app.main_frame.component.buttons.ExitButton;
import com.kevin.music_streaming_app.main_frame.layout.SongBar;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;


public class SongPaneTop extends FlowPane {
    public SongPaneTop(Song song) {
        this.setAlignment(Pos.CENTER_RIGHT);

        ExitButton exitButton = new ExitButton(e -> {
            AppStage.getRoot().getChildren().remove(1);
            AppStage.getBorderPane().setBottom(new SongBar(song));
        }, 25);

        this.getChildren().addAll(exitButton);
    }

}
