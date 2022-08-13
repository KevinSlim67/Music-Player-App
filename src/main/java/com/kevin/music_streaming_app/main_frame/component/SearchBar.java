package com.kevin.music_streaming_app.main_frame.component;

import com.kevin.music_streaming_app.AppStage;
import com.kevin.music_streaming_app.db.Song;
import com.kevin.music_streaming_app.db.User;
import com.kevin.music_streaming_app.main_frame.layout.Center;
import com.kevin.music_streaming_app.main_frame.sections.HomePane;
import com.kevin.music_streaming_app.main_frame.sections.SearchResultsPane;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.util.List;

public class SearchBar extends HBox {

    Button searchBtn = new Button();
    TextField searchBar;
    ChoiceBox selectSearchType;

    public SearchBar() {
        this.setSpacing(5);
        this.setAlignment(Pos.CENTER);

        selectSearchType =
                new ChoiceBox(FXCollections.observableArrayList("Artist", "Song"));
        selectSearchType.setValue("Song");
        selectSearchType.setStyle("-fx-font-size: 14px;");

        searchBar = new TextField();
        searchBar.setPromptText("Search");
        searchBar.setStyle("-fx-font-size: 14px");

        Image searchIcon = new Image("file:assets/icons/search.png");
        ImageView searchImageView = new ImageView(searchIcon);
        searchImageView.setFitWidth(25);
        searchImageView.setFitHeight(25);
        searchImageView.setPreserveRatio(true);
        searchBtn.setGraphic(searchImageView);
        searchBtn.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> searchEnter());
        searchBtn.addEventFilter(MouseEvent.MOUSE_EXITED, e -> searchLeave());
        searchBtn.setStyle("-fx-background-color: transparent");
        searchBtn.setOnAction(e -> searchClick());

        this.getChildren().addAll(selectSearchType, searchBar, searchBtn);
    }

    private void searchClick() {
        String selectedCategory = (String) selectSearchType.getSelectionModel().getSelectedItem();
        String searchQuery = searchBar.getText();
        Center center = AppStage.getCenter();

        if (!searchQuery.isEmpty()) {
            center.getChildren().clear(); //clears current pane
            if (selectedCategory.equals("Song")) {
                List<Song> newList = Song.returnAllThatContain(searchQuery, Integer.MAX_VALUE);
                center.setPane(new SearchResultsPane(newList));
            } else if (selectedCategory.equals("Artist")) {
                List<User> newList = User.returnAllThatContain(searchQuery, Integer.MAX_VALUE);
                center.setPane(new SearchResultsPane(newList));
            }
        }
    }

    private void searchEnter() {
        Image searchImage = new Image("file:assets/icons/search_hovered.png");
        ImageView searchImageView = new ImageView(searchImage);
        searchImageView.setFitWidth(25);
        searchImageView.setFitHeight(25);
        searchImageView.setPreserveRatio(true);

        searchBtn.setGraphic(searchImageView);
    }

    private void searchLeave() {
        Image searchImage = new Image("file:assets/icons/search.png");
        ImageView searchImageView = new ImageView(searchImage);
        searchImageView.setFitWidth(25);
        searchImageView.setFitHeight(25);
        searchImageView.setPreserveRatio(true);

        searchBtn.setGraphic(searchImageView);
    }
}
