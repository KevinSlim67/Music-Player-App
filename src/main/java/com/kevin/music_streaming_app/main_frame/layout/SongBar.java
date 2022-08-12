package com.kevin.music_streaming_app.main_frame.layout;

import com.kevin.music_streaming_app.db.Song;
import javafx.scene.layout.BorderPane;

public class SongBar extends BorderPane {
    Song song;

    public SongBar(Song song) {
        this.song = song;
    }
}
