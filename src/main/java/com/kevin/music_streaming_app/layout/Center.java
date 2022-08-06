package com.kevin.music_streaming_app.layout;

import com.kevin.music_streaming_app.audio.AudioPlayer;
import com.kevin.music_streaming_app.sections.NewReleases;
import javafx.scene.layout.VBox;

import java.sql.Blob;

public class Center extends VBox {
    private static Thread thread;
    private static AudioPlayer player;

    public Center() {
        this.setSpacing(20);

        NewReleases latestSongs = new NewReleases();
        NewReleases latestSongs1 = new NewReleases();
        NewReleases latestSongs2 = new NewReleases();

        this.getChildren().addAll(latestSongs, latestSongs1, latestSongs2);

    }

    public static void playNewSong(Blob song) {

    }

    public static AudioPlayer getPlayer() {
        return player;
    }
}
