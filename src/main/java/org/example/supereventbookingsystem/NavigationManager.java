package org.example.supereventbookingsystem;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

public class NavigationManager{
    private static Stage primaryStage;

    public static void setPrimaryStage(Stage stage){
        primaryStage = stage;
    }

    public static void navigateTo(String pageName){
        try{
            String fxmlPath = getFxmlPath(pageName);

            URL resourceUrl = NavigationManager.class.getResource(fxmlPath);
            if(resourceUrl == null){
                throw new IOException("Resource not found: " + fxmlPath);
            }


            FXMLLoader loader = new FXMLLoader(resourceUrl);
            Parent root = loader.load();

            if (pageName.equals("main")) {
                MainController controller = loader.getController();

            }
            // Handle checkout controller initialization
            if (pageName.equals("checkout")) {
                CheckoutController controller = loader.getController();
                User currentUser = SessionManager.getCurrentUser();
                if (currentUser != null) {
                    controller.setCartItems(currentUser.getAllBookedEvents());
                }
            }

            Scene scene = primaryStage.getScene();
            if (scene == null) {
                scene = new Scene(root);
                primaryStage.setScene(scene);
            } else {
                scene.setRoot(root);
            }

            if(pageName.equals("login")){
                primaryStage.setWidth(400);
                primaryStage.setHeight(600);
            }
            else {
                primaryStage.setWidth(997);
                primaryStage.setHeight(600);
            }
            primaryStage.setResizable(false);
            primaryStage.show();
        }catch(IOException e){
            System.err.println("Navigation error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static String getFxmlPath(String pageName){
        return switch (pageName.toLowerCase()){
            case "login" -> "/org/example/supereventbookingsystem/loginForm.fxml";
            case "main" -> "/org/example/supereventbookingsystem/mainApp.fxml";
            case "checkout" -> "/org/example/supereventbookingsystem/checkout.fxml";
            default -> throw new IllegalArgumentException("Unknown page: " + pageName);
        };
    }
}