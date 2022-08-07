package com.kevin.music_streaming_app.main_frame.layout;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class SideBar extends VBox {

    private Button[] buttons = {
            new Button("Liked Songs"),
            new Button("My Songs"),
            new Button("My Playlists"),
            new Button("Upload Song")
    };

    public SideBar() {
        this.getStyleClass().add("sidebar");
        this.setPrefWidth(130);
        this.setStyle("-fx-border-style: hidden solid hidden hidden; -fx-border-color: white;");
        this.setSpacing(5);
        this.setPadding(new Insets(0, 10, 0, 10));

        for (int i = 0; i < buttons.length; i++) {
            this.getChildren().add(buttons[i]);
        }
    }
}
