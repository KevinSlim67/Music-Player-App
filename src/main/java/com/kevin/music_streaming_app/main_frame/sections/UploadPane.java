package com.kevin.music_streaming_app.main_frame.sections;

import com.kevin.music_streaming_app.StageManager;
import com.kevin.music_streaming_app.db.Song;
import com.kevin.music_streaming_app.db.User;
import com.kevin.music_streaming_app.features.Visibility;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;


public class UploadPane extends VBox {
    HBox box;
    private TextField songName;
    private ChoiceBox genre;
    private FileChooser imageChooser, songChooser;
    private User user;
    private File selectedImage =  new File("assets/placeholder.jpg"), selectedSong;
    private StackPane imagePane;
    private Label songChosen;
    private Label errorMessage = new Label();
    private Label successMessage = new Label("Song successfully uploaded!");

    public UploadPane(User user) {
        this.user = user;
        this.getStyleClass().add("upload-pane");
        this.setSpacing(40);

        Label label = new Label("Upload Song");
        label.setStyle("-fx-font-size: 25px; -fx-font-weight: bold;");

        box = new HBox(40);
        createLeft();
        createRight();

        this.getChildren().addAll(label, box);
    }

    private void createRight() {
        VBox vBox = new VBox(20);
        errorMessage.setStyle("-fx-text-fill: red");
        Visibility.hide(errorMessage);

        successMessage.setStyle("-fx-text-fill: green");
        Visibility.hide(successMessage);

        songChosen = new Label("");
        songChosen.setVisible(false);
        songChosen.setManaged(false);

        songChooser = new FileChooser();
        songChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("MP3 Files", "*.mp3")
                , new FileChooser.ExtensionFilter("WAV Files", "*.wav")
        );

        songName = new TextField();
        songName.setPromptText("Song Name");
        songName.setMaxWidth(200);
        songName.setPrefWidth(200);

        genre = new ChoiceBox(FXCollections.observableArrayList("Pop", "Rock", "Country", "Electronic", "Chill", "Instrumental", "Jazz", "Classical", "Metal"));
        genre.setValue("Genre");
        genre.setMaxWidth(200);
        genre.setPrefWidth(200);

        Button chooseSong = new Button("Choose Song");
        chooseSong.setOnAction(e -> selectSong());

        Button submit = new Button("Submit");
        submit.setOnAction(e -> submitClick());

        vBox.getChildren().addAll(songName, genre, chooseSong, songChosen, submit, errorMessage, successMessage);
        box.getChildren().add(vBox);
    }

    private void createLeft() {
        VBox vBox = new VBox(20);
        vBox.setAlignment(Pos.TOP_CENTER);

        imageChooser = new FileChooser();
        imageChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG Files", "*.png")
                , new FileChooser.ExtensionFilter("JPG Files", "*.jpg")
        );

        imagePane = new StackPane();
        imagePane.setPrefWidth(100);
        imagePane.setPrefHeight(100);
        imagePane.setStyle("-fx-border-color: white; -fx-border-width: 2;");

        Button chooseImage = new Button("Choose Image");
        chooseImage.setOnAction(e -> selectImage());

        vBox.getChildren().addAll(imagePane, chooseImage);

        box.getChildren().add(vBox);
    }

    private void selectImage() {
        String imagePath = imageChooser.showOpenDialog(StageManager.getStage()).toURI().toString();
        this.selectedImage = new File(imagePath.replace("%20", " ")
                .replace("file:/", ""));

        Image image = new Image(imagePath);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        imagePane.getChildren().add(imageView);
    }

    private void selectSong() {
        String songPath = songChooser.showOpenDialog(StageManager.getStage())
                .toURI().toString().replace("file:/", "")
                .replace("%20", " ");
        if (songPath != null) this.selectedSong = new File(songPath);
        songChosen.setText(songPath);

        songChosen.setVisible(true);
        songChosen.setManaged(true);

    }

    private boolean addSong() {
        String name = songName.getText();
        String user = this.user.getName();
        String genre = (String) this.genre.getSelectionModel().getSelectedItem();

        Song song = new Song(name, user, selectedSong, selectedImage, genre);
        return song.insert();
    }

    private void submitClick() {
        if (songName.getText().isEmpty()) {
            errorMessage.setText("Please enter a song name!");
            Visibility.showTemporarly(errorMessage, 5000);
        } else if (songChosen.getText().isEmpty()) {
            errorMessage.setText("Please input a song!");
            Visibility.showTemporarly(errorMessage, 5000);
        } else {
            if (addSong()) {
                Visibility.showTemporarly(successMessage, 5000);
            }
        }
    }
}
