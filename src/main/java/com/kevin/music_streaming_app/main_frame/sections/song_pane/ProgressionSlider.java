package com.kevin.music_streaming_app.main_frame.sections.song_pane;

import com.kevin.music_streaming_app.AppStage;
import javafx.geometry.Insets;
import javafx.scene.control.Slider;

public class ProgressionSlider extends Slider {
    public ProgressionSlider() {
        this.setPadding(new Insets(0, 0, 5, 0));
        this.setPrefWidth(400);
        this.setMin(1);
        this.setMax(100);

        this.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            AppStage.getPlayer().pause();
            AppStage.getPlayer().setPausedOnFrame((int) Math.floor((Double)newValue));
            AppStage.getPlayer().resume();
            System.out.println(AppStage.getPlayer().getPausedOnFrame());
        });
    }
}
