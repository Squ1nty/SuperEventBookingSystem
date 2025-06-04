package org.example.supereventbookingsystem;

public class Event {
    private String eventName;
    private String venueName;
    private String day;
    private int price;
    private int ticketsSold;
    private int totalTickets;

    public Event(String eventName, String venueName, String day, int price, int ticketsSold, int totalTickets){
        this.eventName = eventName;
        this.venueName = venueName;
        this.day = day;
        this.price = price;
        this.ticketsSold = ticketsSold;
        this.totalTickets = totalTickets;
    }
}
