package org.example.supereventbookingsystem;

import javafx.beans.property.*;

public class Event {

    private StringProperty eventName = new SimpleStringProperty();
    private StringProperty venueName = new SimpleStringProperty();
    private StringProperty day = new SimpleStringProperty();
    private IntegerProperty price = new SimpleIntegerProperty();
    private IntegerProperty ticketsSold = new SimpleIntegerProperty();
    private IntegerProperty ticketsLeft = new SimpleIntegerProperty();
    private IntegerProperty totalTickets = new SimpleIntegerProperty();

    public Event(String eventName, String venueName, String day, int price, int ticketsSold, int totalTickets){
        setEventName(eventName);
        setVenueName(venueName);
        setDay(day);
        setPrice(price);
        setTicketsSold(ticketsSold);
        setTicketsLeft(totalTickets - ticketsSold);
        setTotalTickets(totalTickets);
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
    public IntegerProperty ticketsLeftProperty(){
        return ticketsLeft;
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
    public void setTicketsLeft(int ticketsLeft){
        this.ticketsLeft.set(ticketsLeft);
    }
    public void setTotalTickets(int totalTickets){
        this.totalTickets.set(totalTickets);
    }
}
