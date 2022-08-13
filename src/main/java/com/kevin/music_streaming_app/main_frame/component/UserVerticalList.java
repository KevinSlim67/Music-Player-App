package com.kevin.music_streaming_app.main_frame.component;

import com.kevin.music_streaming_app.db.User;
import com.kevin.music_streaming_app.main_frame.component.buttons.UserButtonV2;
import javafx.scene.layout.VBox;

import java.util.List;

public class UserVerticalList extends VBox {
    public UserVerticalList(List<User> users, int width, int height) {
        this.setSpacing(5);

        users.forEach(u -> {
            UserButtonV2 button = new UserButtonV2(u, width, height);
            this.getChildren().add(button);
        });
    }
}
