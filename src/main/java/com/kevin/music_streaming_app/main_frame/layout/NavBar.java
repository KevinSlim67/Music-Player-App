package com.kevin.music_streaming_app.main_frame.layout;

import com.kevin.music_streaming_app.LoginStage;
import com.kevin.music_streaming_app.StageManager;
import com.kevin.music_streaming_app.db.User;
import com.kevin.music_streaming_app.main_frame.component.SearchBar;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;

public class NavBar extends BorderPane {
    public NavBar(User user) {
        this.getStyleClass().add("navbar");
        this.setStyle("-fx-background-color: transparent;");
        this.setPadding(new Insets(0, 20, 0, 20));

        SearchBar searchBar = new SearchBar();

        ChoiceBox userName = new ChoiceBox(FXCollections.observableArrayList("Logout"));
        userName.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        userName.setValue(user.getName());


        userName.setOnAction(e -> {
            if (userName.getSelectionModel().isSelected(0)) {
                logout();
            }
        });


        this.setLeft(searchBar);
        this.setRight(userName);
    }

    private void logout() {
        StageManager.getStage().close();
        StageManager.setStage(new LoginStage());
    }
}
