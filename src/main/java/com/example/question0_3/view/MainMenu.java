package com.example.question0_3.view;

import com.example.question0_3.MainApplication;
import com.example.question0_3.controller.ProfileController;
import com.example.question0_3.model.User;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MainMenu extends Application {
    private static Stage stage;
    private static User user = null;

    @FXML
    private Rectangle avatar;

    @FXML
    private Label userScore;

    @FXML
    private Label userName;

    @FXML
    public void initialize() {
        ProfileController.settingAvatar(avatar, MainMenu.getUser());
        userName.setText("user: " + user.getUsername());
        userScore.setText("score: " + user.getScore());
    }

    public void setUser(User user) {
        MainMenu.user = user;
    }

    public static User getUser() {
        return user;
    }

    @Override
    public void start(Stage stage) throws Exception {
        MainMenu.stage = stage;
        Parent root = MainApplication.loadFXML("main-menu");
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    public void newGame(MouseEvent mouseEvent) throws Exception {
        SettingOfGame settingOfGame = new SettingOfGame();
        settingOfGame.start(stage);
    }

    public void profile(MouseEvent mouseEvent) throws Exception {
        ProfileMenu profileMenu = new ProfileMenu();
        profileMenu.start(stage);
    }

    public void scoreBoard(MouseEvent mouseEvent) throws Exception {
        ScoreBoard scoreBoard = new ScoreBoard();
        scoreBoard.start(stage);
    }

    public void exit(MouseEvent mouseEvent) throws Exception {
        LoginMenu loginMenu = new LoginMenu();
        loginMenu.start(stage);
    }
}
