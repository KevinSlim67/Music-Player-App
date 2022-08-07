package com.kevin.music_streaming_app.main_frame.sections.song_pane;
import javafx.scene.control.Slider;

public class SoundSlider extends Slider {
    public SoundSlider() {
        this.setPrefWidth(100);
        this.setMin(0);
        this.setMax(100);

        this.valueProperty().addListener((observableValue, oldValue, newValue) -> {

        });
    }
}
