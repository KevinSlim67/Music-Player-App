package com.kevin.music_streaming_app.main_frame.sections.upload_pane;

import com.kevin.music_streaming_app.StageManager;
import com.kevin.music_streaming_app.db.Song;
import com.kevin.music_streaming_app.db.User;
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


public class UploadPane extends HBox {
    private TextField songName;
    private ChoiceBox genre;
    private FileChooser imageChooser, songChooser;
    private User user;
    private File selectedImage, selectedSong;
    private StackPane imagePane;
    private Label songChosen;

    public UploadPane(User user) {
        this.user = user;
        this.getStyleClass().add("upload-pane");
        this.setSpacing(40);

        createLeft();
        createRight();
    }

    private void createRight() {
        VBox vBox = new VBox(20);

        songChooser = new FileChooser();
        songChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("MP3 Files", "*.mp3")
                ,new FileChooser.ExtensionFilter("WAV Files", "*.wav")
        );

        songName = new TextField();
        songName.setPromptText("Song Name");
        songName.setMaxWidth(200);
        songName.setPrefWidth(200);

        genre = new ChoiceBox(FXCollections.observableArrayList("Pop", "Rock", "Hip-Hop", "Electronic", "Chill", "Instrumental", "Jazz", "Classical", "Metal"));
        genre.setValue("Genre");
        genre.setMaxWidth(200);
        genre.setPrefWidth(200);

        Button chooseSong = new Button("Choose Song");
        chooseSong.setOnAction(e -> selectSong());

        songChosen = new Label("");
        songChosen.setStyle("-fx-text-fill: white");

        Button submit = new Button("Submit");
        submit.setOnAction(e -> addSong());

        vBox.getChildren().addAll(songName, genre, chooseSong, songChosen, submit);
        this.getChildren().add(vBox);
    }

    private void createLeft() {
        VBox vBox = new VBox(20);
        vBox.setAlignment(Pos.TOP_CENTER);

        imageChooser = new FileChooser();
        imageChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG Files", "*.png")
                ,new FileChooser.ExtensionFilter("JPG Files", "*.jpg")
        );

        imagePane = new StackPane();
        imagePane.setPrefWidth(100);
        imagePane.setPrefHeight(100);
        imagePane.setStyle("-fx-border-color: white; -fx-border-width: 2;");

        Button chooseImage = new Button("Choose Image");
        chooseImage.setOnAction(e -> selectImage());

        vBox.getChildren().addAll(imagePane, chooseImage);

        this.getChildren().add(vBox);
    }

    private void selectImage() {
        String imagePath = imageChooser.showOpenDialog(StageManager.getStage()).toURI().toString();
        this.selectedImage = new File(imagePath);

        Image image = new Image(imagePath);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        imagePane.getChildren().add(imageView);
    }

    private void selectSong() {
        selectedSong = songChooser.showOpenDialog(StageManager.getStage());
        this.selectedSong = new File(selectedSong.toURI().toString());
        songChosen.setText(selectedSong.toURI().toString());
    }

    private void addSong() {
        String name = songName.getText();
        String user = this.user.getName();
        String genre = (String) this.genre.getSelectionModel().getSelectedItem();

        Song song = new Song(name, user, selectedSong, selectedImage, genre);
        System.out.println(selectedSong);
        System.out.println(selectedImage);
        song.insert();
    }
}
