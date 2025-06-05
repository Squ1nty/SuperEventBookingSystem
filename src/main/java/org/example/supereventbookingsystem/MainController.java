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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private Stage primaryStage;
    @FXML
    private Label userGreetingLabel;
    @FXML
    private TextField eventNameInput;
    @FXML
    private TextField dayInput;
    @FXML
    private TextField ticketQuantityInput;
    @FXML
    private Label eventErrorLabel;
    @FXML
    private Label dayErrorLabel;
    @FXML
    private Label ticketErrorLabel;

    ArrayList<BookedEvent> bookedEvents = new ArrayList<>();

    private User getCurrentUser() {
        return SessionManager.getCurrentUser();
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

        User currentUser = getCurrentUser();
        if(currentUser != null) {
            userGreetingLabel.setText("Welcome " + currentUser.getUsername() + "!");
        }

    }

    public void displayCurrentEvents() {
        ArrayList<Event> currentEvents = EventRepository.getAllEvents();
        eventData.setAll(currentEvents); // Update observable list
    }

    @FXML
    private void handleLogout(){
        SessionManager.clearSession();
        NavigationManager.navigateTo("login");
    }

    public boolean isInteger(String numInput){
        try {
            Integer.parseInt(numInput);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean bookingExists(String eventName, String day){
        User user = getCurrentUser();
        ArrayList<BookedEvent> allBookedEvents = user.getAllBookedEvents();
        if(allBookedEvents.isEmpty()){
            return false;
        }
        for(int i = 0; i < allBookedEvents.size(); i++){
            if(allBookedEvents.get(i).getEventName().equalsIgnoreCase(eventName)){
                if(allBookedEvents.get(i).getDay().equalsIgnoreCase(day)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean bookingInputValid(){
        //Reset error labels
        eventErrorLabel.setText("");
        dayErrorLabel.setText("");
        ticketErrorLabel.setText("");

        //Check if TextFields are empty
        if(eventNameInput.getText().isEmpty()) {
            eventErrorLabel.setText("Required");
            return false;
        }
        if(dayInput.getText().isEmpty()){
            dayErrorLabel.setText("Required");
            return false;
        }
        if(ticketQuantityInput.getText().isEmpty()){
            ticketErrorLabel.setText("Required");
            return false;
        }
        if(!isInteger(ticketQuantityInput.getText())){
            ticketErrorLabel.setText("Not Valid");
            return false;
        }

        //If not empty, create variables to be tested
        String eventChosen = eventNameInput.getText().toLowerCase();
        String dayChosen = dayInput.getText().toLowerCase();
        int ticketAmount = Integer.parseInt(ticketQuantityInput.getText());

        //searchVerification variable
        boolean isValid = false;

        //Iterating through, searching events for eventChosen
        ArrayList<Event> currentEvents = EventRepository.getAllEvents();
        for(int i = 0; i < currentEvents.size(); i++){
            if(currentEvents.get(i).getEventName().toLowerCase().equalsIgnoreCase(eventChosen)){
                isValid = true;
                break;
            }
        }
        if(!isValid){
            eventErrorLabel.setText("Not Found");
            return false;
        }
        isValid = false;

        int targetValue;
        switch (dayChosen) {
            case "mon":
                targetValue = 1;
                break;
            case "tue":
                targetValue = 2;
                break;
            case "wed":
                targetValue = 3;
                break;
            case "thu":
                targetValue = 4;
                break;
            case "fri":
                targetValue = 5;
                break;
            case "sat":
                targetValue = 6;
                break;
            case "sun":
                targetValue = 7;
                break;
            default:
                dayErrorLabel.setText("3-Char Format!");
                return false; // Invalid input
        }
        int currentValue = LocalDate.now().getDayOfWeek().getValue();

        // Allow booking if target day is today or later in the current week
        if(targetValue < currentValue){
            dayErrorLabel.setText("Too late!");
            return false;
        }

        //Moving onto sorting by day
        ArrayList<Event> eventsFilteredByName = EventRepository.getEventsByName(eventChosen, currentEvents);

        for(int i = 0; i < eventsFilteredByName.size(); i++){
            if(eventsFilteredByName.get(i).getDay().toLowerCase().equalsIgnoreCase(dayChosen)){
                isValid = true;
                break;
            }
        }
        if(!isValid){
            dayErrorLabel.setText("Not Found");
            return false;
        }
        isValid = false;

        //Filter currentEvents ArrayList<>() by "day"
        currentEvents = EventRepository.getEventsByDay(dayChosen, eventsFilteredByName);
        User user = getCurrentUser();

        //Check if booking for current choice already exists
        if(bookingExists(eventChosen, dayChosen)){
            //updateBookedItem(eventChosen, dayChosen, ticketAmount);
        }
        if(EventRepository.seatCheck(ticketAmount, currentEvents)){
            double eventPrice = currentEvents.getFirst().getPrice();
            BookedEvent newBooking = new BookedEvent(eventChosen, dayChosen, ticketAmount, getCurrentUser().getUsername(), currentEvents, eventPrice);
            user.addToBookedItems(newBooking);
            return true;
        }
        ticketErrorLabel.setText("No more!");
        return false;
    }

    public void handleBooking(){
        if(bookingInputValid()){
            System.out.println("Booking has been made!");
        }
        else{
            System.out.println("Something went wrong...");
        }
    }

    public void loadCheckout() {
        User currentUser = SessionManager.getCurrentUser();
        if (currentUser != null && !currentUser.getAllBookedEvents().isEmpty()) {
            NavigationManager.navigateTo("checkout");
        }
    }

}
