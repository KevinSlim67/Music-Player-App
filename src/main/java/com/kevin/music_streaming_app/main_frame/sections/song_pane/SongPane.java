package com.kevin.music_streaming_app.main_frame.sections.song_pane;

import com.kevin.music_streaming_app.db.Song;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class SongPane extends StackPane {
    private SongPaneBottom songPaneBottom;
    private SongPaneCenter songPaneCenter;
    private SongPaneTop songPaneTop;
    private StackPane pane;

    public SongPane(Song song) {
        this.setStyle("-fx-background-color: rgba(0, 0, 0, 0.3)");

        pane = new StackPane();
        pane.setStyle("-fx-background-color: #0d0e1eF3; -fx-border-color: #535353; " +
                "-fx-border-radius: 30px; -fx-background-radius: 30px");
        pane.setPadding(new Insets(10));
        pane.setPrefWidth(600);
        pane.setPrefHeight(600);
        pane.setMaxWidth(600);
        pane.setMaxHeight(600);

        BorderPane borderPane = new BorderPane();

        songPaneCenter = new SongPaneCenter(song.getCover());
        songPaneBottom = new SongPaneBottom(song.getName(), song.getUser());
        songPaneTop = new SongPaneTop(song);

        borderPane.setCenter(songPaneCenter);
        borderPane.setBottom(songPaneBottom);
        borderPane.setTop(songPaneTop);

        pane.getChildren().add(borderPane);

        this.getChildren().add(pane);
    }
}
