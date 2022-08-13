package com.kevin.music_streaming_app;

import com.kevin.music_streaming_app.audio.PausablePlayer;
import com.kevin.music_streaming_app.db.User;
import com.kevin.music_streaming_app.main_frame.layout.Center;
import com.kevin.music_streaming_app.main_frame.layout.NavBar;
import com.kevin.music_streaming_app.main_frame.layout.SideBar;
import com.kevin.music_streaming_app.main_frame.layout.SongBar;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class AppStage extends Stage {

    private static NavBar navBar;
    private static Center center;
    private static SideBar sideBar;
    private static SongBar songBar;

    private static StackPane root;
    private static BorderPane borderPane;

    private static Scene scene;
    private static List<Thread> threads = new ArrayList<>();
    private static PausablePlayer player;
    private static User user;

    public AppStage(User user) {
        this.user = user;

        this.setTitle("Music Player App");
        root = new StackPane();
        root.setStyle("-fx-background-color: #010211;");

        navBar = new NavBar(user);
        center = new Center();
        sideBar = new SideBar();

        borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: transparent;");
        borderPane.setPrefWidth(this.getWidth());
        borderPane.setPrefHeight(this.getHeight());

        BorderPane.setMargin(navBar, new Insets(10, 10, 10, 10));
        BorderPane.setMargin(sideBar, new Insets(20, 10, 20, 0));

        ScrollPane scrollPane = new ScrollPane(center);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background: transparent;"); //removes border
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);


        borderPane.setTop(navBar);
        borderPane.setCenter(scrollPane);
        borderPane.setLeft(sideBar);

        root.getChildren().add(borderPane);

        scene = new Scene(root, 770, 650);
        scene.getStylesheets().add("style.css");
        this.setScene(scene);
        this.show();
    }

    public static List<Thread> getThreads() {
        return threads;
    }

    public static PausablePlayer getPlayer() {
        return player;
    }

    public static void setPlayer(PausablePlayer newPlayer) {
        player = newPlayer;
    }

    public static StackPane getRoot() {
        return root;
    }

    public static Scene returnScene() {
        return scene;
    }

    public static User getUser() {
        return user;
    }

    public static Center getCenter() {
        return center;
    }

    public static SideBar getSideBar() {
        return sideBar;
    }

    public static void setCenter(Center center) {
        AppStage.center = center;
    }

    public static SongBar getSongBar() {
        return songBar;
    }

    public static void setSongBar(SongBar songBar) {
        AppStage.songBar = songBar;
    }

    public static BorderPane getBorderPane() {
        return borderPane;
    }
}
