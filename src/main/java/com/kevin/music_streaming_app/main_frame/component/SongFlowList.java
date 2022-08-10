package com.kevin.music_streaming_app.main_frame.component;

import com.kevin.music_streaming_app.db.Song;
import com.kevin.music_streaming_app.main_frame.component.buttons.SongButton;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class SongFlowList extends VBox {
    List<Song> songs = new ArrayList<Song>();

    public SongFlowList(String t, List<Song> s) {
        this.setMaxWidth(1000);
        this.setSpacing(20);
        this.setStyle("-fx-background-color: transparent;");
        songs.addAll(s);

        Label title = new Label(t);
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold");

        FlowPane songList = new FlowPane(15, 15);
        for (int i = 0; i < songs.size(); i++) {
            SongButton songButton = new SongButton(songs.get(i));
            songList.getChildren().add(songButton);
        }

        this.getChildren().addAll(title, songList);
    }
}
