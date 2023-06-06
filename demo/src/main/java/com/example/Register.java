package com.example;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Register {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField confirmPassword;

    @FXML
    private Button registerButton;

    @FXML
    private Label registerMsg;

    @FXML
    private Button closeButton;

    @FXML
    private void userRegister(ActionEvent event) throws IOException {
        Main m = new Main();
        String enteredUsername = username.getText();
        String enteredPassword = password.getText();
        String confirmedPassword = confirmPassword.getText();

        if (enteredUsername.isEmpty() || enteredPassword.isEmpty() || confirmedPassword.isEmpty()) {
            registerMsg.setText("Please fill in all fields.");
        } else if (!enteredPassword.equals(confirmedPassword)) {
            registerMsg.setText("Passwords do not match.");
        } else if (enteredPassword.length() < 12) {
            registerMsg.setText("Password should have 12 or more characters.");
        } else{
            // Perform registration logic here
            boolean usernameExists = AccountManager.getAccounts().stream()
                    .anyMatch(account -> account.getUsername().equals(enteredUsername));

            if (usernameExists) {
                registerMsg.setText("Username already exists.");
            } else {
                // Create a new account and add it to the AccountManager
                Account newAccount = new Account(enteredUsername, enteredPassword);
                AccountManager.addAccount(newAccount);

                m.changeScene("Login.fxml");
            }
        }
    }

    @FXML
    private void closePage(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        Platform.exit();
        // Close button event handler code
        // You can add code to close the current window or perform any other necessary actions.
    }
}
