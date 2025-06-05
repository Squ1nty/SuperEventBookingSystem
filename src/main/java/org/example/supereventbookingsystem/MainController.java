package org.example.supereventbookingsystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML
    private Stage primaryStage;
    @FXML
    private Label userGreetingLabel;

    private String username;

    public void setUsername(String username) {
        this.username = username;
        userGreetingLabel.setText("Welcome " + this.username + "!");
    }
    public void loadEventsPage(){
        NavigationManager.navigateTo("events");
    }
    public void loadMakeBookingPage(){
        NavigationManager.navigateTo("makeBooking");
    }
}
