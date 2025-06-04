package org.example.supereventbookingsystem;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController {
    @FXML
    private Label userGreetingLabel;

    private String username;

    public void setUsername(String username) {
        this.username = username;
        userGreetingLabel.setText("Welcome " + this.username + "!");
    }
    public void getAllEventsCaller(){
    }
}
