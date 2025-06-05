package org.example.supereventbookingsystem;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.List;

public class CheckoutController {
    @FXML private ListView<HBox> itemsListView;
    @FXML private Label totalPriceLabel;
    @FXML private Button payNowButton;

    private double totalPrice = 0.0;

    public void setCartItems(List<BookedEvent> cartItems) {
        itemsListView.getItems().clear();
        totalPrice = 0.0;

        for (BookedEvent item : cartItems) {
            totalPrice += item.getTotalPrice();
            itemsListView.getItems().add(createItemNode(item));
        }

        updateTotalLabel();
    }

    private HBox createItemNode(BookedEvent item) {
        HBox hbox = new HBox(20);
        hbox.setStyle("-fx-padding: 15; -fx-background-color: white; -fx-background-radius: 5;");

        VBox eventInfo = new VBox(5);
        eventInfo.getChildren().add(new Text("Event: " + item.getEventName()));
        eventInfo.getChildren().add(new Text("Day: " + item.getDay()));
        eventInfo.getChildren().add(new Text("Tickets: " + item.getNumTickets()));

        Label priceLabel = new Label(String.format("$%.2f", item.getTotalPrice()));
        priceLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16; -fx-text-fill: #e74c3c;");

        hbox.getChildren().addAll(eventInfo, priceLabel);
        return hbox;
    }

    private void updateTotalLabel() {
        totalPriceLabel.setText(String.format("$%.2f", totalPrice));
        payNowButton.setDisable(totalPrice <= 0);
    }

    @FXML
    private void handlePayNow() {
        // Payment processing logic
        System.out.println("Processing payment of $" + totalPrice);

        // Clear user's booked events after payment
        User currentUser = SessionManager.getCurrentUser();
        if (currentUser != null) {
            currentUser.clearBookedEvents();
        }

        // Show confirmation and navigate back
        NavigationManager.navigateTo("main");
    }

    @FXML
    private void handleBack() {
        NavigationManager.navigateTo("main");
    }
}