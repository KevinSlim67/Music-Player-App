package com.kevin.music_streaming_app;

import com.kevin.music_streaming_app.audio.PausablePlayer;
import com.kevin.music_streaming_app.db.User;
import com.kevin.music_streaming_app.main_frame.layout.Center;
import com.kevin.music_streaming_app.main_frame.layout.NavBar;
import com.kevin.music_streaming_app.main_frame.layout.SideBar;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class AppStage extends Stage {

    private NavBar navBar;
    private Center center = new Center();
    private SideBar sideBar = new SideBar();

    private static StackPane root = new StackPane();
    private static Scene scene;
    private static List<Thread> threads = new ArrayList<>();
    private static PausablePlayer player;

    public AppStage(User user) {
        root.setStyle("-fx-background-color: #00010D;");

        navBar = new NavBar(user);

        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: transparent;");
        borderPane.setPrefWidth(this.getWidth());
        borderPane.setPrefHeight(this.getHeight());

        BorderPane.setMargin(navBar, new Insets(10, 10, 10, 10));
        BorderPane.setMargin(center, new Insets(20, 10, 0, 10));
        BorderPane.setMargin(sideBar, new Insets(20, 10, 20, 0));


        borderPane.setTop(navBar);
        borderPane.setCenter(center);
        borderPane.setLeft(sideBar);

        ScrollPane scrollPane = new ScrollPane(borderPane);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background: transparent;"); //removes border
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        root.getChildren().add(scrollPane);

        scene = new Scene(root, 750, 600);
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
}
