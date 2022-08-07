package com.kevin.music_streaming_app.main_frame.component;

import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class SearchBar extends HBox {
    public SearchBar() {
        this.setSpacing(10);

        ChoiceBox selectSearchType =
                new ChoiceBox(FXCollections.observableArrayList("Artist", "Song"));
        selectSearchType.setValue("Artists");

        TextField searchBar = new TextField();
        searchBar.setPromptText("Search");

        this.getChildren().addAll(selectSearchType, searchBar);
    }
}
