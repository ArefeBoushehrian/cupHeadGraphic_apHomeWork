package com.example.question0_3.view;

import com.example.question0_3.MainApplication;
import com.example.question0_3.controller.ProfileController;
import com.example.question0_3.model.User;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ProfileMenu extends Application {
    private static Stage stage;

    @FXML
    private Rectangle avatar;

    @FXML
    private Label messageOfPassword;

    @FXML
    private Label messageOfUsername;

    @FXML
    private TextField newPassword;

    @FXML
    private TextField newUsername;

    @FXML
    private Label userName;

    @FXML
    private Label userScore;

    @FXML
    public void initialize() {
        ProfileController.settingAvatar(avatar, MainMenu.getUser());
        userName.setText("user: " + MainMenu.getUser().getUsername());
        userScore.setText("score: " + MainMenu.getUser().getScore());
    }

    public void changeUsername(MouseEvent mouseEvent) {
        String username = newUsername.getText();
        if (User.isUsernameRepeated(username)) {
            newUsername.setStyle("-fx-border-color: red");
            messageOfUsername.setText("Repeated username :(");
        } else if (username.length() == 0) {
            newUsername.setStyle("-fx-border-color: red");
            messageOfUsername.setText("Empty field :(");
        } else {
            User user = MainMenu.getUser();
            user.setUsername(username);
            messageOfUsername.setText("Success");
        }
    }

    public void changePassword(MouseEvent mouseEvent) {
        String password = newPassword.getText();
        if (password.length() == 0) {
            newPassword.setStyle("-fx-border-color: red");
            messageOfPassword.setText("Empty field :(");
        } else {
            User user = MainMenu.getUser();
            user.setPassword(password);
            messageOfPassword.setText("Success");
        }
    }

    public void logout(MouseEvent mouseEvent) throws Exception {
        LoginMenu loginMenu = new LoginMenu();
        loginMenu.start(stage);
    }

    public void removeAccount(MouseEvent mouseEvent) throws Exception {
        MainMenu.getUser().remove();
        LoginMenu loginMenu = new LoginMenu();
        loginMenu.start(stage);
    }

    public void back(MouseEvent mouseEvent) throws Exception {
        MainMenu mainMenu = new MainMenu();
        mainMenu.start(stage);
    }

    public void changeAvatar(MouseEvent mouseEvent) {
        ProfileController.changingAvatar(avatar, MainMenu.getUser());
    }

    public void typing(KeyEvent keyEvent) {
        TextField textField = (TextField) keyEvent.getSource();
        if (textField.getLength() > 0) textField.setStyle("-fx-border-color: gray");
    }

    @Override
    public void start(Stage stage) throws Exception {
        ProfileMenu.stage = stage;
        Parent root = MainApplication.loadFXML("profile-menu");
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
}
