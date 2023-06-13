package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static Stage stg;
    static Account loggedInAccount = null;

    @Override
    public void start(Stage stage) throws IOException {
        stg = stage;
        FXMLLoader fxmlLoader1 = new FXMLLoader(Main.class.getResource("Login.fxml"));

        Scene scene1 = new Scene(fxmlLoader1.load(), 600, 800);

        stage.setTitle("Welcome!");
        stage.setScene(scene1);

        stage.show();
    }

    public static void changeScene(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxml));
        Parent pane = loader.load();
        if (loader.getController() instanceof EditUser) {
            EditUser controller = loader.getController();
            if (loggedInAccount != null) {
                controller.setLoggedInAccount(loggedInAccount);
            }
        }
        stg.getScene().setRoot(pane);
    }

    public static Account getLoggedInAccount() {
        return loggedInAccount;
    }

    public static void setLoggedInAccount(Account account) {
        loggedInAccount = account;
    }



    public static void main(String[] args) {
        launch();
    }
}
