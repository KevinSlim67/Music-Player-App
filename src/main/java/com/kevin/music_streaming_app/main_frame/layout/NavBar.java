package com.kevin.music_streaming_app.main_frame.layout;

import com.kevin.music_streaming_app.AppStage;
import com.kevin.music_streaming_app.LoginStage;
import com.kevin.music_streaming_app.StageManager;
import com.kevin.music_streaming_app.audio.PausablePlayer;
import com.kevin.music_streaming_app.db.User;
import com.kevin.music_streaming_app.features.ImageStyle;
import com.kevin.music_streaming_app.main_frame.component.SearchBar;
import com.kevin.music_streaming_app.main_frame.sections.HomePane;
import com.kevin.music_streaming_app.main_frame.sections.LibraryPane;
import com.kevin.music_streaming_app.main_frame.sections.MySongsPane;
import com.kevin.music_streaming_app.main_frame.sections.UploadPane;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.sql.SQLException;

public class NavBar extends BorderPane {

    Button refreshBtn;

    public NavBar(User user) {
        this.getStyleClass().add("navbar");
        this.setStyle("-fx-background-color: transparent;");
        this.setPadding(new Insets(0, 20, 0, 20));

        SearchBar searchBar = new SearchBar();


        HBox rightPane = new HBox(5);
        rightPane.setAlignment(Pos.CENTER);

        refreshBtn = new Button();
        Image refreshImage = new Image("file:assets/icons/refresh.png");
        ImageView refreshImageView = new ImageView(refreshImage);
        refreshImageView.setFitWidth(20);
        refreshImageView.setFitHeight(20);
        refreshImageView.setPreserveRatio(true);

        refreshBtn.setGraphic(refreshImageView);
        refreshBtn.setOnAction(e -> refreshClick());
        refreshBtn.setStyle("-fx-background-color: transparent;");
        refreshBtn.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> refreshEnter());
        refreshBtn.addEventFilter(MouseEvent.MOUSE_EXITED, e -> refreshLeave());

        ChoiceBox userName = new ChoiceBox(FXCollections.observableArrayList("Logout"));
        userName.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        userName.setValue(user.getName());


        userName.setOnAction(e -> {
            if (userName.getSelectionModel().isSelected(0)) {
                logout();
            }
        });

        Image profilePicture = new Image("file:assets/placeholder.jpg");
        try {
            if (user.getProfilePicture() != null)
            profilePicture = new Image(user.getProfilePicture().getBinaryStream());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ImageView profileView = new ImageView(profilePicture);
        profileView.setPreserveRatio(true);
        profileView.setFitWidth(30);
        profileView.setFitHeight(30);
        ImageStyle.round(profileView, 5555);

        rightPane.getChildren().addAll(refreshBtn, userName, profileView);

        this.setLeft(searchBar);
        this.setRight(rightPane);
    }

    private void logout() {
        PausablePlayer player = AppStage.getPlayer();
        if (player != null) player.stop();
        AppStage.getThreads().clear();
        StageManager.getStage().close();
        StageManager.setStage(new LoginStage());
    }

    private void refreshClick() {
        String selected = AppStage.getSideBar().getSelected();
        User user = AppStage.getUser();
        Center center = AppStage.getCenter();
        center.getChildren().clear(); //clears current pane

        if (selected.equals("Home")) center.setPane(new HomePane(user));
        else if (selected.equals("Library")) center.setPane(new LibraryPane(user));
        else if (selected.equals("My Songs")) center.setPane(new MySongsPane(user));
        else if (selected.equals("Upload Song")) center.setPane(new UploadPane(user));
    }

    private void refreshEnter() {
        Image refreshImage = new Image("file:assets/icons/refresh_hovered.png");
        ImageView refreshImageView = new ImageView(refreshImage);
        refreshImageView.setFitWidth(20);
        refreshImageView.setFitHeight(20);
        refreshImageView.setPreserveRatio(true);

        refreshBtn.setGraphic(refreshImageView);
    }

    private void refreshLeave() {
        Image refreshImage = new Image("file:assets/icons/refresh.png");
        ImageView refreshImageView = new ImageView(refreshImage);
        refreshImageView.setFitWidth(20);
        refreshImageView.setFitHeight(20);
        refreshImageView.setPreserveRatio(true);

        refreshBtn.setGraphic(refreshImageView);
    }
}
