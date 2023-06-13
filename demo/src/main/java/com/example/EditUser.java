package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class EditUser {
    @FXML
    private Label wrongEdit;

    @FXML
    private TextField usernameEdit;

    @FXML
    private PasswordField passwordEdit;

    @FXML
    private PasswordField confirmPasswordEdit;

    private Account loggedInAccount;

    public void setLoggedInAccount(Account account) {
        this.loggedInAccount = account;
        initializeFields();
    }

    private void initializeFields() {
        if (loggedInAccount != null) {
            usernameEdit.setText(loggedInAccount.getUsername());
            passwordEdit.setText(loggedInAccount.getPassword());
            confirmPasswordEdit.setText(loggedInAccount.getPassword());
        }
    }

    @FXML
    private void saveChanges() throws IOException {
        String newUsername = usernameEdit.getText().trim();
        String newPassword = passwordEdit.getText().trim();
        String confirmedPassword = confirmPasswordEdit.getText().trim();

        if (newUsername.isEmpty() || newPassword.isEmpty() || confirmedPassword.isEmpty()) {
            wrongEdit.setText("Please enter your data.");
            return;
        }

        if (!newPassword.equals(confirmedPassword)) {
            wrongEdit.setText("The confirm password does not match.");
            return;
        }

        if (loggedInAccount == null) {
            wrongEdit.setText("No logged-in account found.");
            return;
        }

        loggedInAccount.setUsername(newUsername);
        loggedInAccount.setPassword(newPassword);

        wrongEdit.setText("Success!");

        // Save the changes to the account (e.g., update the account in the AccountManager)

        // Optionally, navigate back to the chat interface or another page
    }



    @FXML
    private void cancel() throws IOException {
        // Optionally, navigate back to the chat interface or another page
        Main m = new Main();
        m.changeScene("Settings.fxml");
    }
}
