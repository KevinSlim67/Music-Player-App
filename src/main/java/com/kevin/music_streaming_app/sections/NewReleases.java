package com.kevin.music_streaming_app.sections;

import com.kevin.music_streaming_app.component.Song;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class NewReleases extends VBox {
    public NewReleases() {
        this.setSpacing(12);

        Label title = new Label("Latest Songs");

        Font font = Font.font(Font.getDefault().getFamily(), FontWeight.BOLD, 16);
        title.setFont(font);

        HBox songList = new HBox(15);
        Song song1 = new Song();
        Song song2 = new Song();
        Song song3 = new Song();

        songList.getChildren().addAll(song1, song2, song3);

        this.getChildren().addAll(title, songList);
    }
}
