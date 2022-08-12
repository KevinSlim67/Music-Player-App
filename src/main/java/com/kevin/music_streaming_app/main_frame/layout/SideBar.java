package com.kevin.music_streaming_app.main_frame.layout;

import com.kevin.music_streaming_app.AppStage;
import com.kevin.music_streaming_app.db.User;
import com.kevin.music_streaming_app.main_frame.sections.HomePane;
import com.kevin.music_streaming_app.main_frame.sections.LibraryPane;
import com.kevin.music_streaming_app.main_frame.sections.MySongsPane;
import com.kevin.music_streaming_app.main_frame.sections.UploadPane;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class SideBar extends VBox {

    private Button[] buttons = {
            new Button("Home"),
            new Button("Library"),
            new Button("My Songs"),
            new Button("Upload Song")
    };

    private String selected = "Home";
    private Pane centerPane = AppStage.getCenter();
    private User user = AppStage.getUser();

    public SideBar() {
        this.getStyleClass().add("sidebar");
        this.setPrefWidth(130);
        this.setStyle("-fx-border-style: hidden solid hidden hidden; -fx-border-color: white;");
        this.setSpacing(5);
        this.setPadding(new Insets(0, 10, 0, 10));

        for (int i = 0; i < buttons.length; i++) {

            if (buttons[i].getText().equals("Home")) {
                buttons[i].setStyle("-fx-text-fill: #FFC42E");
                buttons[i].setOnAction(e -> clickHome());
            } else if (buttons[i].getText().equals("Library")) {
                buttons[i].setOnAction(e -> clickLibrary());
            } else if (buttons[i].getText().equals("Upload Song")) {
                buttons[i].setOnAction(e -> clickUpload());
            } else if (buttons[i].getText().equals("My Songs")) {
                buttons[i].setOnAction(e -> clickMySongs());
            }
            this.getChildren().add(buttons[i]);
        }
    }

    public Button[] getButtons() {
        return buttons;
    }

    private void clickHome() {
        clearCenter();
        setCenter(new HomePane(user));
        paintButton("Home");
        selected = "Home";
        paintButton(selected);
    }

    private void clickUpload() {
        clearCenter();
        setCenter(new UploadPane(user));
        selected = "Upload Song";
        paintButton(selected);
    }

    private void clickMySongs() {
        clearCenter();
        setCenter(new MySongsPane(user));
        selected = "My Songs";
        paintButton(selected);
    }

    private void clickLibrary() {
        clearCenter();
        setCenter(new LibraryPane(user));
        selected = "Library";
        paintButton(selected);
    }


    private void clearCenter() {
        AppStage.getCenter().getPane().getChildren().clear();
    }

    private void setCenter(Pane pane) {
        AppStage.getCenter().setPane(pane);
    }

    private void paintButton(String selected) {
        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i].getText().equals(selected))
                buttons[i].setStyle("-fx-text-fill: #FFC42E;");
            else
                buttons[i].setStyle("-fx-text-fill: white;");
        }
    }

    public String getSelected() {
        return selected;
    }
}
