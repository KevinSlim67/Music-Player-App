package com.kevin.music_streaming_app.main_frame.sections;

import com.kevin.music_streaming_app.db.Song;
import com.kevin.music_streaming_app.db.User;
import com.kevin.music_streaming_app.main_frame.component.NoSongsMessage;
import com.kevin.music_streaming_app.main_frame.component.SongFlowList;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

import java.util.List;

public class MySongsPane extends VBox {
    User user;

    public MySongsPane(User user) {
        List<Song> list = Song.returnUserSongs(user.getId(), Integer.MAX_VALUE);
        this.user = user;
        //if list is not empty, display the person's song list, otherwise display a message
        //telling them they don't have any songs
        if (!list.isEmpty()) {
            SongFlowList songList = new SongFlowList("My Songs", list);
            this.getChildren().add(songList);
        } else {
            NoSongsMessage message = new NoSongsMessage();
            message.setPadding(new Insets(20, 0, 0, 0));
            this.getChildren().add(message);
        }
    }
}
