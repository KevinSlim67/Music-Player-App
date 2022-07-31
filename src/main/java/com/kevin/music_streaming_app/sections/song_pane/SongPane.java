package com.kevin.music_streaming_app.sections.song_pane;

import com.kevin.music_streaming_app.App;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class SongPane extends StackPane {
    private SongPaneBottom songPaneBottom;
    private SongPaneCenter songPaneCenter;
    private SongPaneTop songPaneTop;
    private BorderPane pane;

    public SongPane() {
        this.setStyle("-fx-background-color: rgba(0, 0, 0, 0.3)");
        double heightPadding = App.getStage().getHeight() * 0.1;
        double widthPadding = App.getStage().getWidth() * 0.1;
        this.setPadding(new Insets(heightPadding, widthPadding, heightPadding, widthPadding));

        pane = new BorderPane();
        pane.setStyle("-fx-background-color: #6d6d6d; -fx-border-color: #535353; " +
                "-fx-border-radius: 30px; -fx-background-radius: 30px");
        pane.setPadding(new Insets(10));


        songPaneCenter = new SongPaneCenter();
        songPaneBottom = new SongPaneBottom();
        songPaneTop = new SongPaneTop();

        pane.setCenter(songPaneCenter);
        pane.setBottom(songPaneBottom);
        pane.setTop(songPaneTop);

        this.getChildren().add(pane);
    }

    public SongPaneBottom getSongPaneBottom() {
        return songPaneBottom;
    }

    public SongPaneCenter getSongPaneCenter() {
        return songPaneCenter;
    }

    public SongPaneTop getSongPaneTop() {
        return songPaneTop;
    }
}
