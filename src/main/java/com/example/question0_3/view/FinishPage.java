package com.example.question0_3.view;

import com.example.question0_3.MainApplication;
import com.example.question0_3.model.Game;
import com.example.question0_3.model.User;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class FinishPage extends Application {
    private static Stage stage;
    private static User user;
    private static Game game;

    @FXML
    private Label score;

    @FXML
    private Label time;

    public void setUser(User user) {
        FinishPage.user = user;
    }

    public void setGame(Game game) {
        FinishPage.game = game;
    }

    @FXML
    public void initialize() {
        score.setText(" your score is: " + game.getScore());
        time.setText("time you finish: " + game.getTimer().getText());
        score.setStyle("-fx-text-fill: white");
        time.setStyle("-fx-text-fill: white");
    }

    @Override
    public void start(Stage stage) throws Exception {
        FinishPage.stage = stage;
        Parent root = MainApplication.loadFXML("finish-view");
        if (game.isWin()) {
            root.getStylesheets().add(FinishPage.class.getResource("/backGround.css").toExternalForm());
            root.getStyleClass().add("win");
        }
        else {
            root.getStylesheets().add(FinishPage.class.getResource("/backGround.css").toExternalForm());
            root.getStyleClass().add("lose");
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    public void restart(MouseEvent mouseEvent) throws Exception {
        Game game = new Game(user, FinishPage.game.getCoefficientOfVulnerability(),
                FinishPage.game.getCoefficientOfWreck(), FinishPage.game.getLevel());
        game.setAudio(null);
        GameView gameView = new GameView();
        gameView.setGame(game);
        gameView.setUser(user);
        gameView.start(stage);
    }

    public void back(MouseEvent mouseEvent) throws Exception {
        if (user != null) {
            MainMenu mainMenu = new MainMenu();
            mainMenu.start(stage);
        } else {
            LoginMenu loginMenu = new LoginMenu();
            loginMenu.start(stage);
        }
    }
}
