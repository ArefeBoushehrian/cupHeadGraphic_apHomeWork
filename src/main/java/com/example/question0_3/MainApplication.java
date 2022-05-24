package com.example.question0_3;

import com.example.question0_3.model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.PublicKey;

public class MainApplication extends Application {
    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    @Override
    public void start(Stage stage) throws IOException {
        DataBase.readFromDatabase();
        MainApplication.stage = stage;
        Parent root = loadFXML("hello-view");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static Parent loadFXML(String name) throws IOException {
        URL address = new URL(MainApplication.class.getResource(name + ".fxml").toExternalForm());
        return FXMLLoader.load(address);
    }

    public static void main(String[] args) throws IOException {
        launch();
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    DataBase.writeOnDataBase();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }));
    }
}