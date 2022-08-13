package com.kevin.music_streaming_app.main_frame.component;

import com.kevin.music_streaming_app.db.Song;
import com.kevin.music_streaming_app.main_frame.component.buttons.SongButtonV2;
import javafx.scene.layout.VBox;

import java.util.List;

public class SongVerticalList extends VBox {
    public SongVerticalList(List<Song> songs, int width, int height) {
        this.setSpacing(5);

        songs.forEach(s -> {
            SongButtonV2 button = new SongButtonV2(s, width, height);
            this.getChildren().add(button);
        });

    }
}
