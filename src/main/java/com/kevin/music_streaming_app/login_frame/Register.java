package com.kevin.music_streaming_app.login_frame;

import com.kevin.music_streaming_app.LoginStage;
import com.kevin.music_streaming_app.db.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.util.Timer;
import java.util.TimerTask;

public class Register extends VBox {
    TextField username = new TextField();;
    PasswordField password = new PasswordField();
    Label errorLabel = new Label("Username already taken!");

    public Register() {
        this.setSpacing(20);
        this.setAlignment(Pos.CENTER);

        username.setPromptText("Username");
        password.setPromptText("Password");

        errorLabel.setStyle("-fx-text-fill: red");
        errorLabel.setVisible(false);
        errorLabel.setManaged(false);

        Button register = new Button("Sign Up");
        register.setPadding(new Insets(5, 10, 5, 10));
        register.setStyle("-fx-font-size: 14px;");
        register.setOnAction(e -> registerClick());

        Label login = new Label("Already have an account ? Login");
        login.setStyle("-fx-background-color: transparent; -fx-border-style: hidden;");

        login.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            LoginStage.getRoot().getChildren().clear();
            LoginStage.setMainPane(new Login());
            LoginStage.getRoot().getChildren().add(LoginStage.getMainPane());
        });

        VBox group = new VBox(12);
        group.setPrefWidth(this.getWidth());
        group.setAlignment(Pos.CENTER);
        login.getStyleClass().add("switch");
        group.getChildren().addAll(login, errorLabel, register);

        this.getChildren().addAll(username, password, group);
    }

    public void registerClick() {
        String username = this.username.getText();
        String password = this.password.getText();

        User user = new User(username, password);
        if (!user.insert()) {
            //if there is no error message, create the error message, and then remove after 3 seconds
            if (!errorLabel.isVisible()) {
                errorLabel.setVisible(true);
                errorLabel.setManaged(true);
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        errorLabel.setVisible(false);
                        errorLabel.setManaged(false);
                    }
                }, 3000);
            }
        };
    }
}
