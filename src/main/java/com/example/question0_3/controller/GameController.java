package com.example.question0_3.controller;

import com.example.question0_3.Enum.AvatarImages;
import com.example.question0_3.Enum.GameImages;
import com.example.question0_3.model.User;
import com.example.question0_3.view.GameView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class GameController {
    public static void settingBoss(Rectangle boss, Stage stage) {
        Image image = new Image(GameController.class.getResource("/frames/BossFly/1.png").toExternalForm());
        ImagePattern bossImage = new ImagePattern(image);
        boss.setFill(bossImage);
        boss.setHeight(100);
        boss.setWidth(100);
        boss.setX(stage.getScene().getWidth() - 120);
        boss.setY((stage.getScene().getHeight() / 2) - 80);
        BossMotion bossMotion = new BossMotion(boss, stage);
        bossMotion.play();
    }

    public static void settingAirplane(Rectangle airplane, User user) {
        Image image;
        if (user == null)
            image = new Image(ProfileController.class.getResource(AvatarImages.AIRPLANE.toString()).toExternalForm());
        else image = new Image(ProfileController.class.getResource(user.getAvatar().toString()).toExternalForm());

        ImagePattern avatarImage = new ImagePattern(image);
        airplane.setFill(avatarImage);
        airplane.setHeight(100);
        airplane.setWidth(100);
    }

    public static void airplaneMotion(Rectangle airplane, Stage stage, Pane pane, User user, Rectangle boss) {
        airplane.setOnKeyPressed(new EventHandler<>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode().equals(KeyCode.DOWN) && airplane.getY() < stage.getScene().getHeight()- 160) {
                    airplane.setY(airplane.getY() + 20);
                } else if (keyEvent.getCode().equals(KeyCode.UP) && airplane.getY() > 0) {
                    airplane.setY(airplane.getY() - 20);
                } else if (keyEvent.getCode().equals(KeyCode.RIGHT) && airplane.getX() < stage.getScene().getWidth() - 120) {
                    airplane.setX(airplane.getX() + 20);
                } else if (keyEvent.getCode().equals(KeyCode.LEFT) && airplane.getX() > 10) {
                    airplane.setX(airplane.getX() - 20);
                } else if (keyEvent.getCode().equals(KeyCode.SPACE)) {
                    setBulletAndMotion(pane, airplane, user, boss);
                }

                if (airplane.getBoundsInParent().intersects(boss.getLayoutBounds())) {
                    if (GameView.getGame().isCupHeadDie()) {
                        pane.getChildren().remove(airplane);
                        pane.getChildren().remove(boss);
                        GameView.getGame().finish();
                    }
                }
            }
        });
    }

    public static Rectangle generateMiniBoss(Stage stage, Pane pane, boolean isPurple) {
        Rectangle miniBoss = new Rectangle();
        miniBoss.setHeight(40);
        miniBoss.setWidth(40);
        miniBoss.setX(stage.getScene().getWidth() - 100);
        miniBoss.setY(stage.getScene().getHeight() - 100);
        Image image;
        if (isPurple) image = new Image(ProfileController.class.getResource("/frames/MiniBossFly/purple/1.png").toExternalForm());
        else image = new Image(ProfileController.class.getResource("/frames/MiniBossFly/yellow/1.png").toExternalForm());
        ImagePattern miniBossImage = new ImagePattern(image);
        miniBoss.setFill(miniBossImage);
        pane.getChildren().add(miniBoss);
        return miniBoss;
    }

    public static Rectangle generateEgg(Stage stage, Pane pane, Rectangle boss) {
        Rectangle egg = new Rectangle();
        egg.setHeight(40);
        egg.setWidth(40);
        egg.setX(boss.getX());
        egg.setY(boss.getY() + 40);

        Image image = new Image(GameController.class.getResource(GameImages.EGG.toString()).toExternalForm());
        ImagePattern eggImage = new ImagePattern(image);
        egg.setFill(eggImage);
        pane.getChildren().add(egg);
        return egg;
    }

    public static void setMiniBossAndMotion(Pane pane, Label time, Stage stage, Rectangle boss, Rectangle airplane) {
        MiniBossesMotions miniBossesMotions = new MiniBossesMotions(time, pane, stage, boss, airplane);
        miniBossesMotions.play();
    }

    private static void setBulletAndMotion(Pane pane, Rectangle airplane, User user, Rectangle boss) {
        Rectangle ball = new Rectangle();
        ball.setHeight(10);
        ball.setWidth(30);
        ball.setX(airplane.getX() + 100);
        ball.setY(airplane.getY() + 60);
        if (user != null && user.getAvatar() != AvatarImages.AIRPLANE) ball.setY(ball.getY() + 15);

        Image image = new Image(ProfileController.class.getResource(GameImages.BULLET.toString()).toExternalForm());
        ImagePattern bulletImage = new ImagePattern(image);
        ball.setFill(bulletImage);
        pane.getChildren().add(ball);
        BallMotion ballMotion = new BallMotion(ball, true, pane, false, boss, airplane);
        ballMotion.play();
    }

    public static void setTimer(Label time) {
        ObjectProperty<Duration> remainingDuration
                = new SimpleObjectProperty<>(java.time.Duration.ofSeconds(0));

        // Binding with media time format (mm:ss):
        time.textProperty().bind(Bindings.createStringBinding(() ->
                        String.format("%02d:%02d",
                                remainingDuration.get().toMinutesPart(),
                                remainingDuration.get().toSecondsPart()),
                remainingDuration));
        Timeline countDownTimeLine = new Timeline(new KeyFrame(javafx.util.Duration.seconds(1), (ActionEvent event) ->
                remainingDuration.setValue(remainingDuration.get().plus(1, ChronoUnit.SECONDS))));

        countDownTimeLine.setCycleCount(Animation.INDEFINITE);
        countDownTimeLine.play();
    }
}
