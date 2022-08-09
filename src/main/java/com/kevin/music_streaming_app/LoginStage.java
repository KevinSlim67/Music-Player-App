package com.kevin.music_streaming_app;

import com.kevin.music_streaming_app.login_frame.Login;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginStage extends Stage {

    private static StackPane root;
    private static VBox mainPane;

    public LoginStage() {
        root = new StackPane();
        root.getStyleClass().add("login");

        root.setStyle("-fx-background-color: #010211;");
        root.setPadding(new Insets(50, 100, 50, 100));

        mainPane = new Login();
        root.getChildren().add(mainPane);

        Scene scene = new Scene(root, 400, 400);
        scene.getStylesheets().add("style.css");

        this.setScene(scene);
        this.setTitle("Music Streaming App");
        this.setResizable(false);
        this.show();
    }

    public static VBox getMainPane() {
        return mainPane;
    }

    public static void setMainPane(VBox mainPane) {
        LoginStage.mainPane = mainPane;
    }

    public static StackPane getRoot() {
        return root;
    }
}
