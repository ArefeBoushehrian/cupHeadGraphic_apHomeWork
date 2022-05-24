package com.example.question0_3.view;

import com.example.question0_3.Enum.LoginMessages;
import com.example.question0_3.MainApplication;
import com.example.question0_3.controller.LoginController;
import com.example.question0_3.model.User;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginMenu extends Application {
    private static Stage stage;

    @FXML
    private Button signInButton;

    @FXML
    private Button loginButton;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    public void enterGuest(MouseEvent mouseEvent) throws Exception {
        SettingOfGame settingOfGame = new SettingOfGame();
        settingOfGame.start(stage);
    }

    public void Login(MouseEvent mouseEvent) throws Exception {
        loginButton.setDisable(true);

        if (username.getLength() == 0) username.setStyle("-fx-border-color: red");
        else if (password.getLength() == 0) password.setStyle("-fx-border-color: red");
        else {
            LoginController loginController = new LoginController();
            LoginMessages messages = loginController.checkInputValidationLogin(username.getText(), password.getText());
            if (messages.equals(LoginMessages.INCORRECT_INFORMATION)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Incorrect information");
                alert.show();
            } else {
                MainMenu mainMenu = new MainMenu();
                mainMenu.setUser(User.getUserByInformation(username.getText(), password.getText()));
                mainMenu.start(stage);
            }
        }
    }

    public void SignIn(MouseEvent mouseEvent) throws Exception {
        signInButton.setDisable(true);

        if (username.getLength() == 0) username.setStyle("-fx-border-color: red");
        else if (password.getLength() == 0) password.setStyle("-fx-border-color: red");
        else {
            LoginController loginController = new LoginController();
            LoginMessages messages = loginController.checkInputValidationCreate(username.getText(), password.getText());
            if (messages.equals(LoginMessages.REPEATED_USERNAME)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Choosing repeated Username");
                alert.show();
            } else {
                User user = new User(username.getText(), password.getText());
                MainMenu mainMenu = new MainMenu();
                mainMenu.setUser(user);
                mainMenu.start(stage);
            }
        }
    }

    public void Typing(KeyEvent keyEvent) {
        TextField text = (TextField) keyEvent.getSource();
        if (text.getLength() > 0) text.setStyle("-fx-border-color: gray");

        if (username.getLength() > 0 && password.getLength() > 0) {
            loginButton.setDisable(false);
            signInButton.setDisable(false);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        LoginMenu.stage = stage;
        Parent root = MainApplication.loadFXML("login-view");
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
}
