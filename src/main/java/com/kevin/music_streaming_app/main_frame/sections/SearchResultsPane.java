package com.kevin.music_streaming_app.main_frame.sections;

import com.kevin.music_streaming_app.db.Song;
import com.kevin.music_streaming_app.db.User;
import com.kevin.music_streaming_app.main_frame.component.ArtistImage;
import com.kevin.music_streaming_app.main_frame.component.SongVerticalList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class SearchResultsPane extends VBox {
    public SearchResultsPane(List list) {
        this.setPadding(new Insets(20, 0, 0, 0));
        this.setSpacing(40);
        this.setPrefWidth(800);

        int numberOfResults = list.size();
        Label text = new Label("We found " + numberOfResults + " result(s) that match your search");
        text.setStyle("-fx-font-size: 23px; -fx-font-weight: bold");

        this.getChildren().add(text);

        if (list != null && !list.isEmpty()) {

            if (list.get(0) instanceof Song) {

                SongVerticalList resultList = new SongVerticalList(list, 600, 50);
                this.getChildren().add(resultList);

            } else if (list.get(0) instanceof User) {

                FlowPane pane = new FlowPane(20, 20);
                List<User> userList = list;
                userList.forEach(el -> {
                    ArtistImage artistImage = new ArtistImage(el, 125, true);
                    pane.getChildren().add(artistImage);
                });
               this.getChildren().add(pane);

                //creates a different song list for every user found
                userList.forEach(el -> {
                    Label artistName = new Label(el.getName());
                    artistName.setStyle("-fx-font-size: 17px; -fx-font-weight: bold");
                    VBox vBox = new VBox(20);

                    List<Song> songList = Song.returnUserSongs(el.getId(), 10);
                    SongVerticalList verticalList = new SongVerticalList(songList, 600, 50);

                    //if the user has no songs, don't show their empty list of songs
                    if (!songList.isEmpty()) {
                        vBox.getChildren().addAll(artistName, verticalList);
                        this.getChildren().add(vBox);
                    }
                });
            }
        }
    }
}
