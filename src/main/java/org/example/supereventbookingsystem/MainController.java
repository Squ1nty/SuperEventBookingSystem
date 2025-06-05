package org.example.supereventbookingsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {
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

    @FXML private TableView<Event> eventsTable;
    @FXML private TableColumn<Event, String> eventCol;
    @FXML private TableColumn<Event, String> venueCol;
    @FXML private TableColumn<Event, String> dayCol;
    @FXML private TableColumn<Event, Integer> priceCol;
    @FXML private TableColumn<Event, Integer> ticketsSoldCol;
    @FXML private TableColumn<Event, Integer> ticketsLeftCol;
    @FXML private TableColumn<Event, Integer> totalTicketsCol;

    private final ObservableList<Event> eventData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Set up column bindings
        eventCol.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        venueCol.setCellValueFactory(new PropertyValueFactory<>("venueName"));
        dayCol.setCellValueFactory(new PropertyValueFactory<>("day"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        ticketsSoldCol.setCellValueFactory(new PropertyValueFactory<>("ticketsSold"));
        ticketsLeftCol.setCellValueFactory(new PropertyValueFactory<>("ticketsLeft"));
        totalTicketsCol.setCellValueFactory(new PropertyValueFactory<>("totalTickets"));

        // Load data into table
        displayCurrentEvents();

        // Connect data to table
        eventsTable.setItems(eventData);
    }
    public void displayCurrentEvents() {
        ArrayList<Event> currentEvents = EventRepository.getAllEvents();
        eventData.setAll(currentEvents); // Update observable list
    }

    @FXML
    private void handleRefresh() {
        displayCurrentEvents(); // Refresh data
    }
    @FXML
    private void handleLogout(){
        NavigationManager.navigateTo("login");
    }
}
