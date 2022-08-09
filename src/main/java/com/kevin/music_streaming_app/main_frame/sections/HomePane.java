package com.kevin.music_streaming_app.main_frame.sections;

import com.kevin.music_streaming_app.db.Song;
import com.kevin.music_streaming_app.db.User;
import com.kevin.music_streaming_app.main_frame.component.SongList;
import javafx.scene.layout.VBox;

public class HomePane extends VBox {
    public HomePane(User user) {
        this.setSpacing(20);
        SongList latestSongs = new SongList("Newest Releases", Song.returnNewest(5));
        SongList likedSongs = new SongList("Liked Songs", Song.returnLiked(user.getId(), 5));
        this.getChildren().addAll(latestSongs, likedSongs);
    }
}
