package com.kevin.music_streaming_app.main_frame.layout;

import com.kevin.music_streaming_app.audio.AudioPlayer;
import com.kevin.music_streaming_app.main_frame.sections.NewReleases;
import javafx.scene.layout.VBox;

public class Center extends VBox {
    private static Thread thread;
    private static AudioPlayer player;

    public Center() {
        this.setSpacing(20);
        this.setStyle("-fx-background-color: transparent");

        NewReleases latestSongs = new NewReleases();
        NewReleases latestSongs1 = new NewReleases();
        NewReleases latestSongs2 = new NewReleases();

        this.getChildren().addAll(latestSongs, latestSongs1, latestSongs2);

    }
}
