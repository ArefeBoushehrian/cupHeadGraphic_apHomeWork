package com.example.question0_3.view;

import com.example.question0_3.MainApplication;
import com.example.question0_3.controller.GameController;
import com.example.question0_3.controller.ProfileController;
import com.example.question0_3.model.Game;
import com.example.question0_3.model.User;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GameView extends Application {
    private static Stage stage;
    private static Game game;
    private static User user;

    @FXML
    private Label score;

    @FXML
    private Label health;

    @FXML
    private Label bossHealth;

    @FXML
    private Rectangle boss;

    @FXML
    private Label time;

    @FXML
    private Pane pane;

    @FXML
    private Rectangle airplane;

    @FXML
    private Rectangle avatar;

    @FXML
    private Label userName;

    @FXML
    public void initialize() {
        if (user != null) {
            ProfileController.settingAvatar(avatar, user);
            userName.setText("user: " + user.getUsername());
        }
        else userName.setText("user: guest");

        if (game.getAudio() != null) game.getAudio().play();

        GameController.settingAirplane(airplane, user);
        GameController.settingBoss(boss, stage);
        Platform.runLater(() -> airplane.requestFocus());
        GameController.airplaneMotion(airplane, stage, pane, user, boss);

        GameController.setTimer(time);
        GameController.setMiniBossAndMotion(pane, time, stage, boss, airplane);
        game.setTimer(time);
        game.setBossHealthLabel(bossHealth);
        game.setHealthLabel(health);
        game.setScoreLabel(score);
    }

    public void setUser(User user) {
        GameView.user = user;
    }

    public void setGame(Game game) {
        GameView.game = game;
    }

    public static Game getGame() {
        return game;
    }

    @Override
    public void start(Stage stage) throws Exception {
        GameView.stage = stage;
        Parent root = MainApplication.loadFXML("game-view");
        Scene scene = new Scene(root);
        root.setStyle("-fx-background-image: url(/background.png)");
        stage.setScene(scene);
    }

    public static void goToFinishPage(Game game) {
        FinishPage finishPage = new FinishPage();
        finishPage.setGame(game);
        finishPage.setUser(user);
        try {
            finishPage.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
