package com.kevin.music_streaming_app.main_frame.sections;

import com.kevin.music_streaming_app.AppStage;
import com.kevin.music_streaming_app.db.Song;
import com.kevin.music_streaming_app.db.User;
import com.kevin.music_streaming_app.main_frame.component.ArtistImage;
import com.kevin.music_streaming_app.main_frame.component.SongVerticalList;
import com.kevin.music_streaming_app.main_frame.component.UserVerticalList;
import com.kevin.music_streaming_app.main_frame.component.buttons.CategoryButton;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.util.List;


public class LibraryPane extends VBox {
    private User user;
    private SongVerticalList songList = new SongVerticalList(Song.returnLiked(AppStage.getUser().getId()
            , Integer.MAX_VALUE), 600, 50);

    private UserVerticalList userList;
    private VBox vBox;

    private Label categoryName = new Label("Liked Songs");

    public LibraryPane(User user) {
        this.user = user;
        this.setSpacing(40);
        this.setPrefWidth(800);

        Label title = new Label("Library");
        title.setStyle("-fx-font-size: 25px; -fx-font-weight: bold;");

        categoryName.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        FlowPane selectionBox = new FlowPane(20, 20);

        CategoryButton likedCategory = new CategoryButton("Liked",
                new Image("file:assets/icons/gray_heart.png"));
        likedCategory.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            categoryName.setText("Liked Songs");
            likedList();
        });

        CategoryButton followedCategory = new CategoryButton("Followed Artists",
                new Image("file:assets/icons/gray_person.png"));
        followedCategory.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            categoryName.setText("Followed Artists");
            followedList();
        });

        selectionBox.getChildren().addAll(likedCategory, followedCategory);

        vBox = new VBox(20);
        vBox.getChildren().addAll(categoryName, songList);

        this.getChildren().addAll(title, selectionBox, vBox);
    }

    private void likedList() {
        if (vBox.getChildren().size() == 3) {
            vBox.getChildren().remove(2);
        }

        vBox.getChildren().remove(1);
        songList = new SongVerticalList(Song.returnLiked(user.getId()
                , Integer.MAX_VALUE), 600, 50);
        vBox.getChildren().add(songList);
    }

    private void followedList() {
        vBox.getChildren().remove(1);
        FlowPane pane = new FlowPane(20, 20);

        List<User> userList = user.returnFollowed(Integer.MAX_VALUE);

        userList.forEach(el -> {
            ArtistImage artistImage = new ArtistImage(el, 125, true);
            artistImage.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> createSongList(el));
            pane.getChildren().add(artistImage);
        });

        vBox.getChildren().add(pane);
    }

    private void createSongList(User user) {

        Label artistName = new Label(user.getName());
        artistName.setStyle("-fx-font-size: 17px; -fx-font-weight: bold");

        List<Song> songList = Song.returnUserSongs(user.getId(), 10);
        SongVerticalList verticalList = new SongVerticalList(songList, 600, 50);

        VBox box = new VBox(20);
        box.setPadding(new Insets(20, 0, 0, 0));

        if (!songList.isEmpty()) {
            box.getChildren().addAll(artistName, verticalList);
        }

        if (vBox.getChildren().size() == 3) {
            vBox.getChildren().remove(2);
        }
        vBox.getChildren().add(box);
    }
}