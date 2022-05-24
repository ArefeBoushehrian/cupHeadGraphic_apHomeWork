package com.example.question0_3.model;

import com.example.question0_3.Enum.AvatarImages;
import com.example.question0_3.Enum.LevelOfHard;

import java.util.ArrayList;
import java.util.Comparator;

public class User {
    private static ArrayList<User> listOfAllUsers = new ArrayList<>();

    private String username;
    private String password;
    private int score = 0;
    private String timeRemain = "0";
    private LevelOfHard level = null;
    private String levelOfGame = "";
    private AvatarImages avatar = null;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        listOfAllUsers.add(this);
    }

    public void setScore(int score, String time, Game game) {
        if (score > this.score || (score == this.score && time.compareTo(timeRemain) < 0)) {
            timeRemain = time;
            this.score = score;
            this.level = game.getLevel();
            this.levelOfGame = this.level.toString();
        }
    }

    public void setAvatar(AvatarImages avatar) {
        this.avatar = avatar;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getScore() {
        return score;
    }

    public String getTimeRemain() {
        return timeRemain;
    }

    public String getLevelOfGame() {
        return levelOfGame;
    }

    public LevelOfHard getLevel() {
        return level;
    }

    public static ArrayList<User> getListOfAllUsers() {
        return listOfAllUsers;
    }

    public static void setListOfAllUsers(ArrayList<User> listOfAllUsers) {
        User.listOfAllUsers = listOfAllUsers;
    }

    public AvatarImages getAvatar() {
        return avatar;
    }

    public void remove() {
        listOfAllUsers.remove(this);
    }

    public static User getUserByInformation(String username, String password) {
        for (User user: listOfAllUsers) {
            if (user.username.equals(username) && user.getPassword().equals(password)) return user;
        }

        return null;
    }

    public static boolean isUsernameRepeated(String username) {
        for (User user: listOfAllUsers) {
            if (user.getUsername().equals(username)) return true;
        }

        return false;
    }

    public static boolean isUsernameAndPasswordValid(String username, String password) {
        for (User user: listOfAllUsers) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) return true;
        }

        return false;
    }

    public static void sort() {
        Comparator<User> compareByScore = Comparator.comparing(User::getScore).reversed();
        Comparator<User> compareByTime = Comparator.comparing(User::getTimeRemain);

        Comparator<User> full = compareByScore.thenComparing(compareByTime);
        User.listOfAllUsers.sort(full);
    }
}
