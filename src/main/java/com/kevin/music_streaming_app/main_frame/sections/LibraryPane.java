package com.kevin.music_streaming_app.main_frame.sections;

import com.kevin.music_streaming_app.AppStage;
import com.kevin.music_streaming_app.db.Song;
import com.kevin.music_streaming_app.db.User;
import com.kevin.music_streaming_app.main_frame.component.SongVerticalList;
import com.kevin.music_streaming_app.main_frame.component.buttons.CategoryButton;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;


public class LibraryPane extends VBox {
    User user;
    SongVerticalList list = new SongVerticalList(Song.returnLiked(AppStage.getUser().getId()
            , Integer.MAX_VALUE), 600, 50);

    Label categoryName = new Label("Liked Songs");

    public LibraryPane(User user) {
        this.user = user;
        this.setSpacing(40);

        Label title = new Label("Library");
        title.setStyle("-fx-font-size: 25px; -fx-font-weight: bold;");

        categoryName.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        FlowPane selectionBox = new FlowPane(20, 20);

        CategoryButton likedCategory = new CategoryButton("Liked");
        likedCategory.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            categoryName.setText("Liked Songs");
            likedList();
        });

        selectionBox.getChildren().addAll(likedCategory);

        VBox vBox = new VBox(20);
        vBox.getChildren().addAll(categoryName, list);

        this.getChildren().addAll(title, selectionBox, vBox);
    }

    private void likedList() {
        this.getChildren().remove(list);
        list = new SongVerticalList(Song.returnLiked(AppStage.getUser().getId()
                , Integer.MAX_VALUE), 600, 50);
        this.getChildren().add(list);
    }
}