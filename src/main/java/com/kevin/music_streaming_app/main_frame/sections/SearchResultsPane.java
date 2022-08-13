package com.kevin.music_streaming_app.main_frame.sections;

import com.kevin.music_streaming_app.db.Song;
import com.kevin.music_streaming_app.db.User;
import com.kevin.music_streaming_app.main_frame.component.SongVerticalList;
import com.kevin.music_streaming_app.main_frame.component.UserVerticalList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.List;

public class SearchResultsPane extends VBox {
    public SearchResultsPane(List list) {
        this.setPadding(new Insets(20, 0, 0, 0));
        this.setSpacing(40);

        int numberOfResults = list.size();
        Label text = new Label("We found " + numberOfResults + " results that match your search");
        text.setStyle("-fx-font-size: 23px; -fx-font-weight: bold");

        this.getChildren().add(text);

        if (list != null && !list.isEmpty()) {
            if (list.get(0) instanceof Song) {
                SongVerticalList resultList = new SongVerticalList(list, 600, 50);
                this.getChildren().add(resultList);
            } else if (list.get(0) instanceof User) {
                UserVerticalList resultList = new UserVerticalList(list, 600, 50);
                this.getChildren().add(resultList);
            }
        }

    }

}
