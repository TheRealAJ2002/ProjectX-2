package com.example.demo;

import javafx.event.ActionEvent;

import java.io.IOException;

public class AfterLogin {

    public void userLogout(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("Login.fxml");
    }


}
