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

    @FXML Button cancelButton;

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
    }  else if (!isValidPassword(enteredPassword, confirmedPassword)) {
        registerMsg.setText("Password did not contain all safety requirements, or passwords did not match");
    } else if (!isValidUsername(enteredUsername)) {
        registerMsg.setText("Username should have 6 or more characters.");
    } else {
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

public boolean isValidUsername(String username) {
    return username.length() >= 6;
}

    public boolean isValidPassword(String password, String confirmedPassword) {
        // Check password length
        if (password.length() < 12) {
            return false;
        }
        // Check if password contains a number
        if (!password.matches(".*\\d.*")) {
            return false;
        }
        // Check if passwords match
        if (!password.equals(confirmedPassword)) {
            return false;
        }
        // All checks passed, password is valid
        return true;
    }

    public boolean isValidUserRegistration(String username, String password, String confirmedPassword) {
        boolean usernameExists = AccountManager.getAccounts().stream()
                .anyMatch(account -> account.getUsername().equals(username));

        return !usernameExists && isValidUsername(username) && isValidPassword(password, confirmedPassword);
    }

    @FXML
    private void navBack(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("Login.fxml");
    }


}






