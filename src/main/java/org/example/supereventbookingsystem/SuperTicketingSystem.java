package org.example.supereventbookingsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SuperTicketingSystem extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SuperTicketingSystem.class.getResource("loginForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Login - SuperTicketingSystem");
        stage.setScene(scene);
        stage.setResizable(false);

        LoginController controller = fxmlLoader.getController();
        controller.setPrimaryStage(stage);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}