package com.example.question0_3.view;

import com.example.question0_3.Enum.LevelOfHard;
import com.example.question0_3.MainApplication;
import com.example.question0_3.model.Game;
import com.example.question0_3.model.User;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.net.MalformedURLException;

public class SettingOfGame extends Application {
    private static Stage stage;
    private final User user = MainMenu.getUser();
    private boolean haveMusic = true;

    @FXML
    public ComboBox<String> audio;

    @FXML
    private ComboBox<String> levelSelect;

    @FXML
    public void initialize() {
        levelSelect.getItems().setAll("first degree", "second degree", "third degree");
        audio.getItems().setAll("yes", "mute");
    }

    private void setThingsOfGameView(Game game, GameView gameView) throws MalformedURLException {
        gameView.setUser(user);
        gameView.setGame(game);

        if (!haveMusic)  game.setAudio(null);
        else {
            Media audio = new Media(getClass().getResource("/audio1.wav").toExternalForm());
            MediaPlayer mediaPlayer = new MediaPlayer(audio);
            mediaPlayer.setAutoPlay(true);
            game.setAudio(mediaPlayer);
        }
    }

    public void startEasy() throws Exception {
        Game game = new Game(user, 50, 150, LevelOfHard.EASY);
        GameView gameView = new GameView();
        setThingsOfGameView(game, gameView);
        gameView.start(stage);
    }

    public void startIntermediate() throws Exception {
        Game game = new Game(user, 100, 100, LevelOfHard.INTERMEDIATE);
        GameView gameView = new GameView();
        setThingsOfGameView(game, gameView);
        gameView.start(stage);
    }

    public void startHard() throws Exception {
        Game game = new Game(user, 150, 50, LevelOfHard.HARD);
        GameView gameView = new GameView();
        setThingsOfGameView(game, gameView);
        gameView.start(stage);
    }

    @Override
    public void start(Stage stage) throws Exception {
        SettingOfGame.stage = stage;
        Parent root = MainApplication.loadFXML("setting-of-game");
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    public void runGame(MouseEvent mouseEvent) throws Exception {
        haveMusic = audio.getValue() == null || audio.getValue().equals("yes");

        if (levelSelect.getValue() == null) startIntermediate();
        else if (levelSelect.getValue().equals("first degree")) startEasy();
        else if (levelSelect.getValue().equals("second degree")) startIntermediate();
        else startHard();
    }
}
