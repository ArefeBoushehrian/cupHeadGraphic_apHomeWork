package com.example.question0_3.controller;

import javafx.animation.Transition;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

public class MiniBossesMotions extends Transition {
    private Label time;
    private Pane pane;
    private Stage stage;
    private boolean isAnimated = false;
    private Rectangle boss;
    private Rectangle airplane;

    public MiniBossesMotions(Label time, Pane pane, Stage stage, Rectangle boss, Rectangle airplane) {
        this.time = time;
        this.pane = pane;
        this.stage = stage;
        this.boss = boss;
        this.airplane = airplane;
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        int second = Integer.parseInt(time.getText().split(":")[1]);
        if (!pane.getChildren().contains(boss) || !pane.getChildren().contains(airplane)) {
            this.stop();
            return;
        }

        if (second % 10 == 1 && !isAnimated) {
            isAnimated = true;

            if (pane.getChildren().contains(boss)){
                Rectangle egg = GameController.generateEgg(stage, pane, boss);
                egg.setY(boss.getY() + 40);
                egg.setX(boss.getX());
                BallMotion ballMotionEgg = new BallMotion(egg, false, pane, false, boss, airplane);
                ballMotionEgg.play();
            }

            Rectangle bossPurple = GameController.generateMiniBoss(stage, pane, true);
            Rectangle bossYellow = GameController.generateMiniBoss(stage, pane, false);
            Random random = new Random();
            double y = random.nextDouble(stage.getScene().getHeight() - 150) + 50;
            bossPurple.setY(y);
            bossYellow.setY(y + 50);
            bossPurple.setX(stage.getScene().getWidth() - 100);
            bossYellow.setX(stage.getScene().getWidth() - 100);
            BallMotion ballMotionPurple = new BallMotion(bossPurple, false, pane, true, boss, airplane);
            BallMotion ballMotionYellow = new BallMotion(bossYellow, false, pane, true, boss, airplane);
            ballMotionPurple.play();
            ballMotionYellow.play();
        } else if (second % 10 == 2) isAnimated = false;
    }
}
