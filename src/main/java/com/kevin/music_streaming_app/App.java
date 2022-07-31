package com.kevin.music_streaming_app;

import com.kevin.music_streaming_app.audio.AudioPlayer;
import com.kevin.music_streaming_app.db.DB;

import com.kevin.music_streaming_app.layout.Center;
import com.kevin.music_streaming_app.layout.NavBar;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {

    private static Stage stage;
    private static Scene scene;
    private static StackPane root;
    private NavBar navBar = new NavBar();
    private Center center = new Center();

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        root = new StackPane();
        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: transparent;");

        BorderPane.setMargin(navBar, new Insets(10, 10, 10, 10));
        BorderPane.setMargin(center, new Insets(20, 10, 0, 10));

        borderPane.setTop(navBar);
        borderPane.setCenter(center);

        ScrollPane scrollPane = new ScrollPane(borderPane);
        scrollPane.setStyle("-fx-background-color: transparent;"); //removes border
        root.getChildren().add(scrollPane);

        scene = new Scene(root, 600, 500);
        //scene.setFill(new Color(0, 0.01, 0.13, 1));
        scene.getStylesheets().add("style.css");
        stage.setTitle("Music Streaming App");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        DB.main(); //runs the database
        AudioPlayer audioPlayer = new AudioPlayer();
        audioPlayer.start();
        launch();
    }

    public static Scene getScene() {
        return scene;
    }

    public static StackPane getRoot() {
        return root;
    }

    public static Stage getStage() {
        return stage;
    }
}
