package com.kevin.music_streaming_app.main_frame.component;

import com.kevin.music_streaming_app.main_frame.component.buttons.SongButton;
import com.kevin.music_streaming_app.db.Song;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.List;

public class SongList extends VBox {
    List<Song> songs = new ArrayList<Song>();

    public SongList(String t, List<Song> s) {

        //the component isn't show if the list is empty
        if (s.isEmpty()) {
            this.setManaged(false);
            this.setVisible(false);
        }

        this.setSpacing(12);
        this.setStyle("-fx-background-color: transparent;");
        songs.addAll(s);

        Label title = new Label(t);

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
