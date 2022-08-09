package com.kevin.music_streaming_app.main_frame.layout;

import com.kevin.music_streaming_app.AppStage;
import com.kevin.music_streaming_app.db.User;
import com.kevin.music_streaming_app.main_frame.sections.HomePane;
import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Center extends VBox {

    private Pane pane;

    public Center() {
        this.setPadding(new Insets(20));
        this.setStyle("-fx-background-color: transparent");

        User user = AppStage.getUser();
        pane = new HomePane(user);
        this.getChildren().add(pane);

    }

    public Pane getPane() {
        return pane;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
        this.getChildren().add(pane);
    }
}
