package com.kevin.music_streaming_app.main_frame.component;

import javafx.scene.control.Label;

public class NoSongsMessage extends Label {
    public NoSongsMessage() {
        this.setText("Sorry, you don't have any song! :(");
        this.setStyle("-fx-font-size: 25;");
    }

    public NoSongsMessage(String text) {
        this.setText(text);
        this.setStyle("-fx-font-size: 25;");
    }
}
