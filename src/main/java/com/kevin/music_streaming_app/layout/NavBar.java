package com.kevin.music_streaming_app.layout;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class NavBar extends HBox {
    public NavBar() {
        this.setSpacing(25);
        this.setAlignment(Pos.CENTER);

        ChoiceBox selectSearchType =
                new ChoiceBox(FXCollections.observableArrayList("Artist", "Song"));

        selectSearchType.setValue("Artists");

        TextField searchBar = new TextField();
        searchBar.setPromptText("Search");

        Label userName = new Label("Username");
        userName.setStyle("-fx-font-size: 15px;");

        this.getChildren().addAll(selectSearchType, searchBar, userName);
    }
}
