package com.example.question0_3.controller;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BossMotion extends Transition {
    private Rectangle boss;
    private Stage stage;

    public BossMotion(Rectangle boss, Stage stage) {
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(-1);
        this.boss = boss;
        this.stage = stage;
    }

    @Override
    protected void interpolate(double v) {
        int file = (int) Math.floor(10 * v);
        if (file < 6) file += 1;
        else file = 11 - file;

        Image image = new Image(GameController.class.getResource("/frames/BossFly/" + file + ".png").toExternalForm());
        ImagePattern imagePattern = new ImagePattern(image);
        boss.setFill(imagePattern);
        boss.setX(stage.getScene().getWidth() - 120);
        boss.setY((stage.getScene().getHeight() / 2) - 80);
    }
}
