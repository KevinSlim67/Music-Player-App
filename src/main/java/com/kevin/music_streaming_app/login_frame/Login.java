package com.kevin.music_streaming_app.login_frame;

import com.kevin.music_streaming_app.AppStage;
import com.kevin.music_streaming_app.LoginStage;
import com.kevin.music_streaming_app.StageManager;
import com.kevin.music_streaming_app.db.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class Login extends VBox {
    TextField username = new TextField();;
    PasswordField password = new PasswordField();

    public Login() {
        this.setSpacing(20);
        this.setAlignment(Pos.CENTER);

        username.setPromptText("Username");
        password.setPromptText("Password");

        Button login = new Button("Login");
        login.setPadding(new Insets(5, 10, 5, 10));
        login.setStyle("-fx-font-size: 14px;");
        login.setOnAction(e -> loginClick());

        Label register = new Label("Don't have an account ? Register");
        register.setStyle("-fx-background-color: transparent; -fx-border-style: hidden;");
        register.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            LoginStage.getRoot().getChildren().clear();
            LoginStage.setMainPane(new Register());
            LoginStage.getRoot().getChildren().add(LoginStage.getMainPane());
        });

        VBox group = new VBox(12);
        group.setPrefWidth(this.getWidth());
        group.setAlignment(Pos.CENTER);
        group.getChildren().addAll(register, login);

        this.getChildren().addAll(username, password, group);
    }

    private void loginClick() {
        String username = this.username.getText();
        String password = this.password.getText();

        Object[] object = User.verifyLogin(username, password);
        boolean result = (boolean) object[0];
        if (result) {
            User user = (User) object[1];
            StageManager.getStage().close();
            StageManager.setStage(new AppStage(user));
        }
    }
}
