package com.kevin.music_streaming_app.main_frame.sections;

import com.kevin.music_streaming_app.db.Song;
import com.kevin.music_streaming_app.db.User;
import com.kevin.music_streaming_app.main_frame.component.SongVerticalList;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class LibraryPane extends VBox {
    User user;

    public LibraryPane(User user) {
        this.user = user;
        this.setSpacing(20);

        Label title = new Label("Library");
        title.setStyle("-fx-font-size: 25px; -fx-font-weight: bold;");

        SongVerticalList list = new SongVerticalList(Song.returnNewest(20));

        this.getChildren().addAll(title, list);
    }
}