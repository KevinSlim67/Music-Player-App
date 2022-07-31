package com.kevin.music_streaming_app.layout;

import com.kevin.music_streaming_app.sections.NewReleases;
import javafx.scene.layout.VBox;

public class Center extends VBox {
    public Center() {
        this.setSpacing(20);

        NewReleases latestSongs = new NewReleases();
        NewReleases latestSongs1 = new NewReleases();
        NewReleases latestSongs2 = new NewReleases();

        this.getChildren().addAll(latestSongs, latestSongs1, latestSongs2);

    }
}
