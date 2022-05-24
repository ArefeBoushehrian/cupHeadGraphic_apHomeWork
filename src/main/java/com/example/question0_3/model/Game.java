package com.example.question0_3.model;

import com.example.question0_3.Enum.LevelOfHard;
import com.example.question0_3.view.FinishPage;
import com.example.question0_3.view.GameView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.media.MediaPlayer;

public class Game {
    private User user;
    private double health;
    private int coefficientOfVulnerability;
    private int coefficientOfWreck;
    private LevelOfHard level;
    private MediaPlayer audio;
    private int score = 0;
    private double bossHealth = 10;
    private Label timer;
    private Label bossHealthLabel;
    private Label scoreLabel;
    private Label healthLabel;

    public Game(User user, int coefficientOfVulnerability, int coefficientOfWreck, LevelOfHard level) {
        this.user = user;
        this.coefficientOfVulnerability = coefficientOfVulnerability;
        this.coefficientOfWreck = coefficientOfWreck;
        this.level = level;
        this.health = level.getHealth();
    }

    public LevelOfHard getLevel() {
        return level;
    }

    public double getHealth() {
        return health;
    }

    public int getCoefficientOfVulnerability() {
        return coefficientOfVulnerability;
    }

    public int getCoefficientOfWreck() {
        return coefficientOfWreck;
    }

    public int getScore() {
        return score;
    }

    public Label getTimer() {
        return timer;
    }

    public void setAudio(MediaPlayer audio) {
        this.audio = audio;
    }

    public void setTimer(Label timer) {
        this.timer = timer;
    }

    public void setBossHealthLabel(Label bossHealthLabel) {
        this.bossHealthLabel = bossHealthLabel;
        this.bossHealthLabel.setText("boss health percentage: " + bossHealth * 10);
    }

    public void setScoreLabel(Label scoreLabel) {
        this.scoreLabel = scoreLabel;
        this.scoreLabel.setText("score: " + score);
    }

    public void setHealthLabel(Label healthLabel) {
        this.healthLabel = healthLabel;
        this.healthLabel.setText("health: " + health);
    }

    public MediaPlayer getAudio() {
        return audio;
    }

    public void increaseScore() {
        this.score++;
        this.scoreLabel.setText("score: " + score);
    }

    public boolean isBossDie(int turn) {
        bossHealth -= ((double) coefficientOfWreck * turn) / 100.0;
        this.bossHealthLabel.setText("boss health percentage: " + bossHealth * 10);
        return bossHealth < 0.1;
    }

    public boolean isCupHeadDie() {
        health -= ((double) coefficientOfVulnerability) / 100.0;
        this.healthLabel.setText("health: " + health);
        return health < 0.1;
    }
    public boolean isWin() {
        return !(health < 0.1);
    }

    public void finish() {
        score += health * 2;
        score -= bossHealth * 2;
        if (bossHealth == 0) score += 20;
        String time = timer.getText();
        audio.stop();

        if (user != null) user.setScore(score, time, this);
        GameView.goToFinishPage(this);
    }
}
