package com.kevin.music_streaming_app.main_frame.sections;

import com.kevin.music_streaming_app.db.Song;
import com.kevin.music_streaming_app.db.User;
import com.kevin.music_streaming_app.main_frame.component.SongList;
import javafx.scene.layout.VBox;

public class HomePane extends VBox {
    public HomePane(User user) {
        this.setSpacing(20);
        SongList latestSongs = new SongList("Newest Releases", Song.returnNewest(12));
        SongList likedSongs = new SongList("Liked Songs", Song.returnLiked(user.getId(), 12));
        SongList popSongs = new SongList("Pop Songs", Song.returnGenre("Pop", 12));
        SongList chillSongs = new SongList("Chill Songs", Song.returnGenre("Chill", 12));
        SongList instrumentalSongs = new SongList("Instrumental Songs", Song.returnGenre("Instrumental", 12));
        SongList metalSongs = new SongList("Metal Songs", Song.returnGenre("Metal", 12));
        this.getChildren().addAll(latestSongs, likedSongs, popSongs, chillSongs, instrumentalSongs,
                metalSongs);
    }
}
