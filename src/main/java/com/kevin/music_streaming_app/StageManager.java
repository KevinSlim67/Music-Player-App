package com.kevin.music_streaming_app;

import com.kevin.music_streaming_app.db.DB;
import javafx.application.Application;
import javafx.stage.Stage;

public class StageManager extends Application {

    private static Stage stage;

    @Override
    public void start(Stage stage) {
        stage = new LoginStage();
        this.stage = stage;
    }

    public static void main(String args[]) {
        DB.main();
        launch(args);
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        StageManager.stage = stage;
    }
}
