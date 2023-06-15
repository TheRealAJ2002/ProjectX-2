package com.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SettingsController {

    @FXML
    private Button LogOutButton;

    @FXML
    private ToggleButton themeToggle;


    @FXML
    private ComboBox<String> languageDropdown;
    private ObservableList<String> languageOptions = FXCollections.observableArrayList("English", "Dutch", "German");


    @FXML
    private void initialize() {
        Settings settings = new Settings();
        languageDropdown.setItems(languageOptions);

        // Set the initial selected language based on the ChatController's selectedLanguage
        languageDropdown.setValue(settings.getSelectedLanguage());

        languageDropdown.setOnAction(event -> {
            // Get the selected language
            String selectedLanguage = languageDropdown.getValue();
            settings.setSelectedLanguage(selectedLanguage);
        });

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

    @FXML
    private void logOut() throws IOException {
        Main.changeScene("Login.fxml");
    }
}
