package com.example.question0_3;

import com.example.question0_3.view.LoginMenu;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() throws Exception {
        welcomeText.setText("Welcome to JavaFX Application!");
        (new LoginMenu()).start(MainApplication.getStage());
    }
}