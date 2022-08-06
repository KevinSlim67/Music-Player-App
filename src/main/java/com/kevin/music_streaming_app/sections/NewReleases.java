package com.kevin.music_streaming_app.sections;

import com.kevin.music_streaming_app.component.SongButton;
import com.kevin.music_streaming_app.db.Song;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.List;

public class NewReleases extends VBox {
    List<Song> songs = new ArrayList<Song>();

    public NewReleases() {
        this.setSpacing(12);
        songs.addAll(Song.returnAll("newest"));

        Label title = new Label("Latest Songs");

        Font font = Font.font(Font.getDefault().getFamily(), FontWeight.BOLD, 16);
        title.setFont(font);

        HBox songList = new HBox(15);
        for (int i = 0; i < songs.size(); i++) {
            SongButton songButton = new SongButton(songs.get(i));
            songList.getChildren().add(songButton);
        }

        this.getChildren().addAll(title, songList);
    }
}
