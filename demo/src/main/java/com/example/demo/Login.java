package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Login {

    public Login(){

    }

    @FXML
    private Button button;
    @FXML
    private Label wrongLogin;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    public void userLogin (ActionEvent event) throws IOException {
        checkLogin();

    }

    public void registerPage (ActionEvent event) throws  IOException {
        navRegister();
    }

    private void navRegister() throws IOException{
        Main m = new Main();
        m.changeScene("Register.fxml");
    }

    private void checkLogin() throws IOException {
        Main m = new Main();
        String enteredUsername = username.getText().trim();
        String enteredPassword = password.getText().trim();

        if (enteredUsername.isEmpty() || enteredPassword.isEmpty()) {
            wrongLogin.setText("Please enter your data.");
        } else {
            // Check if the entered username and password match any account in the AccountManager
            boolean loginSuccessful = AccountManager.getAccounts().stream()
                    .anyMatch(account -> account.getUsername().equals(enteredUsername)
                            && account.getPassword().equals(enteredPassword));

            if (loginSuccessful) {
                wrongLogin.setText("Success!");
                m.changeScene("afterLogin.fxml");
            } else {
                wrongLogin.setText("Wrong username or password");
            }
        }
    }



}
