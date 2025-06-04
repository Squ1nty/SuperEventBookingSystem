package org.example.supereventbookingsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

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

    public static void initEvents(){
        File file = new File("events.dat");
        ArrayList<Event> eventList = new ArrayList<>();

        try(Scanner scanner = new Scanner(file)){
            while(scanner.hasNextLine()){
                int counter = 1;
                String line = scanner.nextLine().trim();
                String[] columns = line.split(";");

                String eventName = columns[0];
                String venueName = columns[1];
                String day = columns[2];
                int price = Integer.parseInt(columns[3]);
                int ticketsSold = Integer.parseInt(columns[4]);
                int totalTickets = Integer.parseInt(columns[5]);

                Event event = new Event(eventName, venueName, day, price, ticketsSold, totalTickets);
                eventList.add(event);
            }
            EventRepository.initEventList(eventList);
        }catch(FileNotFoundException e){
            System.err.println("File not found: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        initEvents();
        launch(args);
    }
}