package org.example.supereventbookingsystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

import java.io.IOException;

public class LoginController {
    @FXML
    private Stage primaryStage;
    @FXML
    private TextField usernameInput;
    @FXML
    private Label usernameInputResponseLabel;
    @FXML
    private Label pwdInputResponseLabel;
    @FXML
    private PasswordField pwdInput;

    public void setPrimaryStage(Stage stage){
        this.primaryStage = stage;
    }

    public void handleSubmit(){
        if (credentialsIsValid()){
            NavigationManager.navigateTo("main");
        }
    }
    private boolean credentialsIsValid(){
        usernameInputResponseLabel.setTextFill(Color.web("#ff281e"));
        usernameInputResponseLabel.setText("");
        pwdInputResponseLabel.setTextFill(Color.web("#ff281e"));
        pwdInputResponseLabel.setText("");

        if(usernameInput.getText().isEmpty()) {
            usernameInputResponseLabel.setText("Error | Username is required");
            return false;
        }
        if(pwdInput.getText().isEmpty()){
            usernameInputResponseLabel.setText("");
            pwdInputResponseLabel.setText("Error | Password is required");
            return false;
        }
        String username = usernameInput.getText();
        String pwd = pwdInput.getText();
        pwdInputResponseLabel.setText("");

        for(int i = 1; i <= 3; i++) {
            if(username.equals("user" + i) && pwd.equals("password" + i)){
                return true;
            }
        }
        pwdInputResponseLabel.setText("Username and/or password are incorrect");
        return false;
    }
}