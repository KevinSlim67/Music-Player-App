package com.kevin.music_streaming_app.main_frame.component;

import com.kevin.music_streaming_app.AppStage;
import com.kevin.music_streaming_app.main_frame.component.buttons.SongButton;
import com.kevin.music_streaming_app.db.Song;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.List;

public class SongList extends ScrollPane {
    List<Song> songs = new ArrayList<Song>();

    public SongList(String t, List<Song> s) {
        this.setStyle("-fx-background-color: transparent;");
        this.setPadding(new Insets(5, 0, 5, 0));
        this.setVbarPolicy(ScrollBarPolicy.NEVER);
        this.setHbarPolicy(ScrollBarPolicy.ALWAYS);
        this.setFitToHeight(true);
        this.setFitToHeight(true);

        VBox vBox = new VBox(12);
        vBox.setPrefWidth(1000);

        //the component isn't show if the list is empty
        if (s.isEmpty()) {
            this.setManaged(false);
            this.setVisible(false);
        }

        songs.addAll(s);

        Label title = new Label(t);

        Font font = Font.font(Font.getDefault().getFamily(), FontWeight.BOLD, 16);
        title.setFont(font);

        HBox songList = new HBox(15);
        for (int i = 0; i < songs.size(); i++) {
            SongButton songButton = new SongButton(songs.get(i));
            songList.getChildren().add(songButton);
        }

        vBox.getChildren().addAll(title, songList);

        this.setContent(vBox);
    }
}
