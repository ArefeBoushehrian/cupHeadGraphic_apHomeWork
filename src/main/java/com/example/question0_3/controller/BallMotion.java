package com.example.question0_3.controller;

import com.example.question0_3.view.GameView;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;

public class BallMotion extends Transition {
    private static ArrayList<Rectangle> list = new ArrayList<>();

    private Rectangle ball;
    private int speed = 3;
    private boolean rightToLeft;
    private Pane pane;
    private int health = 1;
    private Rectangle boss;
    private Rectangle airplane;

    public BallMotion(Rectangle ball, boolean rightToLeft, Pane pane, boolean isMiniBoss, Rectangle boss, Rectangle airplane) {
        this.ball = ball;
        this.rightToLeft = rightToLeft;
        this.pane = pane;
        this.boss = boss;
        this.airplane = airplane;
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(-1);
        if (!rightToLeft) speed = -1;
        list.add(ball);
        if (isMiniBoss) health = 2;
    }

    @Override
    protected void interpolate(double v) {
        if (!list.contains(ball)) {
            this.stop();
            return;
        }
        if (!pane.getChildren().contains(boss) || !pane.getChildren().contains(airplane)) {
            this.stop();
            return;
        }

        double dx = speed;
        ball.setX(ball.getX() + dx);

        if (!rightToLeft) {
            for (Rectangle rectangle: list) {
                if (pane.getChildren().contains(rectangle) &&
                        !ball.equals(rectangle) && ball.getBoundsInParent().intersects(rectangle.getLayoutBounds())) {
                    if (this.health == 2) {
                        this.health--;
                        pane.getChildren().remove(rectangle);
                    } else {
                        list.remove(ball);
                        this.stop();
                        pane.getChildren().remove(ball);
                        list.remove(rectangle);
                        pane.getChildren().remove(rectangle);
                        GameView.getGame().increaseScore();
                        return;
                    }
                }
            }

            if (ball.getBoundsInParent().intersects(airplane.getLayoutBounds())) {
                pane.getChildren().remove(ball);
                list.remove(ball);
                this.stop();
                if (GameView.getGame().isCupHeadDie()) {
                    pane.getChildren().remove(airplane);
                    list = new ArrayList<>();
                    GameView.getGame().finish();
                }
                return;
            }
        } else {
           if (pane.getChildren().contains(ball) && ball.getBoundsInParent().intersects(boss.getLayoutBounds())) {
               list.remove(ball);
               pane.getChildren().remove(ball);
               this.stop();
               if (GameView.getGame().isBossDie(1)) {
                   pane.getChildren().remove(boss);
                   list = new ArrayList<>();
                   GameView.getGame().finish();
               }
               return;
           }
        }

        if (ball.getX() < -500) {
            list.remove(ball);
            this.stop();
        }
    }
}
