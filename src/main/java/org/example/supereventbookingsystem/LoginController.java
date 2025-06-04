package org.example.supereventbookingsystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
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
        if (credentialsIsValid()) {
            loadMainApp();
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
    private void loadMainApp(){
        try {
            // 1. Load main application FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("mainApp.fxml"));
            Parent mainAppRoot = loader.load();

            // 2. Get current scene and replace content
            Scene currentScene = primaryStage.getScene();

            // 3. Preserve current window size/position
            double currentWidth = currentScene.getWidth();
            double currentHeight = currentScene.getHeight();

            // 4. Replace root node
            currentScene.setRoot(mainAppRoot);

            // 5. Adjust window settings
            primaryStage.setTitle("Main Application");
            primaryStage.setMinWidth(800);
            primaryStage.setMinHeight(600);

            // 6. Optional: Maintain previous size or set new
            primaryStage.setWidth(Math.max(currentWidth, 800));
            primaryStage.setHeight(Math.max(currentHeight, 600));

            // 7. Inject services/data into main controller if needed
            MainController mainController = loader.getController();
            mainController.setUsername(usernameInput.getText());

        } catch (IOException e) {
            // Handle transition error
            System.out.println("Application failed to load: " + e.getMessage());
            e.printStackTrace();
        }
    }
}