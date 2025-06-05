package org.example.supereventbookingsystem;

import javafx.beans.property.*;

public class Event{

    private final StringProperty eventName = new SimpleStringProperty("");
    private final StringProperty venueName = new SimpleStringProperty("");
    private final StringProperty day = new SimpleStringProperty("");
    private final IntegerProperty price = new SimpleIntegerProperty(0);
    private final IntegerProperty ticketsSold = new SimpleIntegerProperty(0);
    private final ReadOnlyIntegerWrapper ticketsLeft = new ReadOnlyIntegerWrapper();
    private final IntegerProperty totalTickets = new SimpleIntegerProperty(0);

    public Event() {
        setupTicketsLeftBinding();
    }

    public Event(String eventName, String venueName, String day, int price, int ticketsSold, int totalTickets) {
        this.eventName.set(eventName);
        this.venueName.set(venueName);
        this.day.set(day);
        this.price.set(price);
        this.ticketsSold.set(ticketsSold);
        this.totalTickets.set(totalTickets);
        setupTicketsLeftBinding();
    }

    private void setupTicketsLeftBinding() {
        ticketsLeft.bind(totalTickets.subtract(ticketsSold));
    }

    //Property Getters
    public StringProperty eventNameProperty(){
        return eventName;
    }
    public StringProperty venueNameProperty(){
        return venueName;
    }
    public StringProperty dayProperty(){
        return day;
    }
    public IntegerProperty priceProperty(){
        return price;
    }
    public IntegerProperty ticketsSoldProperty(){
        return ticketsSold;
    }
    public ReadOnlyIntegerProperty ticketsLeftProperty() {
        return ticketsLeft.getReadOnlyProperty();
    }
    public IntegerProperty totalTicketsProperty(){
        return totalTickets;
    }

    //Regular Getters
    public String getEventName(){
        return eventName.get();
    }
    public String getVenueName(){
        return venueName.get();
    }
    public String getDay(){
        return day.get();
    }
    public int getPrice(){
        return price.get();
    }
    public int getTicketsSold(){
        return ticketsSold.get();
    }
    public int getTicketsLeft(){
        return ticketsLeft.get();
    }
    public int getTotalTickets(){
        return totalTickets.get();
    }

    //Setters
    public void setEventName(String eventName){
        this.eventName.set(eventName);
    }
    public void setVenueName(String venueName){
        this.venueName.set(venueName);
    }
    public void setDay(String day){
        this.day.set(day);
    }
    public void setPrice(int price){
        this.price.set(price);
    }
    public void setTicketsSold(int ticketsSold){
        this.ticketsSold.set(ticketsSold);
    }
    public void setTicketsLeft(){
        ticketsLeft.bind(totalTicketsProperty().subtract(ticketsSoldProperty()));
    }
    public void setTotalTickets(int totalTickets){
        this.totalTickets.set(totalTickets);
    }
}
