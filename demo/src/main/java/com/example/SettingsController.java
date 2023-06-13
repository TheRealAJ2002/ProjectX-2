package com.example;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SettingsController {
    @FXML
    private ComboBox<String> languageDropdown;

    @FXML
    private ToggleButton themeToggle;

    @FXML
    private void navEditUser() throws IOException {
        Main.changeScene("EditUser.fxml");
    }

    @FXML
    private void initialize() {
        languageDropdown.getItems().addAll("Nederlands", "Engels", "Duits");

        themeToggle.setOnAction(event -> {
            Scene scene = themeToggle.getScene();
            if (themeToggle.isSelected()) {
                // Code to switch to dark mode
                scene.getStylesheets().clear();
                scene.getStylesheets().add(getClass().getResource("dark.css").toExternalForm());
            } else {
                // Code to switch to light mode
                scene.getStylesheets().clear();
                scene.getStylesheets().add(getClass().getResource("light.css").toExternalForm());
            }
        });
    }

    @FXML
    private void goBack() throws IOException {
        Main.changeScene("Chat.fxml");
    }
}
