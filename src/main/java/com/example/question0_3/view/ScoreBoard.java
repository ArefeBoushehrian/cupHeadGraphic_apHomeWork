package com.example.question0_3.view;

import com.example.question0_3.Enum.LevelOfHard;
import com.example.question0_3.MainApplication;
import com.example.question0_3.model.User;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ScoreBoard extends Application {
    private static Stage stage;

    @FXML
    private TableColumn<User, LevelOfHard> levelColumn;

    @FXML
    private TableColumn<User, String> usernameColumn;

    @FXML
    private TableColumn<User, Integer> scoreColumn;

    @FXML
    private TableColumn<User, Integer> timeColumn;

    @FXML
    private TableView scoreBoard;

    @FXML
    public void initialize() {
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("timeRemain"));
        levelColumn.setCellValueFactory(new PropertyValueFactory<>("levelOfGame"));
        ObservableList<User> items = FXCollections.<User>observableArrayList();

        User.sort();
        for (int i = 0; i < 10 && i < User.getListOfAllUsers().size(); i++) {
            items.add(User.getListOfAllUsers().get(i));
        }
        
        scoreBoard.getItems().addAll(items);
    }

    @Override
    public void start(Stage stage) throws Exception {
        ScoreBoard.stage = stage;
        Parent root = MainApplication.loadFXML("score-board");
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    public void back(MouseEvent mouseEvent) throws Exception {
        MainMenu mainMenu = new MainMenu();
        mainMenu.start(stage);
    }

    public void easySort(MouseEvent mouseEvent) {
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("timeRemain"));
        levelColumn.setCellValueFactory(new PropertyValueFactory<>("levelOfGame"));
        ObservableList<User> items = FXCollections.<User>observableArrayList();

        User.sort();
        for (User user: User.getListOfAllUsers()) {
            if (user.getLevel() == LevelOfHard.EASY) items.add(user);
        }

        scoreBoard.setItems(items);
    }

    public void intermediateSort(MouseEvent mouseEvent) {
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("timeRemain"));
        levelColumn.setCellValueFactory(new PropertyValueFactory<>("level"));
        ObservableList<User> items = FXCollections.<User>observableArrayList();

        User.sort();
        for (User user: User.getListOfAllUsers()) {
            if (user.getLevel() == LevelOfHard.INTERMEDIATE) items.add(user);
        }

        scoreBoard.setItems(items);
    }

    public void hardSort(MouseEvent mouseEvent) {
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("timeRemain"));
        levelColumn.setCellValueFactory(new PropertyValueFactory<>("level"));
        ObservableList<User> items = FXCollections.<User>observableArrayList();

        User.sort();
        for (User user: User.getListOfAllUsers()) {
            if (user.getLevel() == LevelOfHard.HARD) items.add(user);
        }

        scoreBoard.setItems(items);
    }

    public void devilSort(MouseEvent mouseEvent) {
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("timeRemain"));
        levelColumn.setCellValueFactory(new PropertyValueFactory<>("level"));
        ObservableList<User> items = FXCollections.<User>observableArrayList();

        User.sort();
        for (User user: User.getListOfAllUsers()) {
            if (user.getLevel() == LevelOfHard.DEVIL_MODE) items.add(user);
        }

        scoreBoard.setItems(items);
    }
}
