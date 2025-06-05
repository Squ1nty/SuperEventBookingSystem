package org.example.supereventbookingsystem;

import java.util.ArrayList;

public class BookedEvent extends Event{
    String eventName = "";
    String dayChosen = "";
    String username = "";
    int numTickets = 0;
    double unitPrice = 0;
    ArrayList<Event> eventList = new ArrayList<>();

    public BookedEvent(){}

    public BookedEvent(String eventName, String dayChosen, int numTickets, String username, ArrayList<Event> eventList, double unitPrice){
        this.eventName = eventName;
        this.dayChosen = dayChosen;
        this.username = username;
        this.eventList = eventList;
        setNumTickets(numTickets);
        updateTickets(numTickets, this.eventList);
        this.unitPrice = unitPrice;
    }
    public void updateTickets(int numTickets, ArrayList<Event> eventList){
        if(numTickets > 0){
            //Buying ticket
            Event bookedEvent = eventList.getFirst();
            int numTicketsSold = bookedEvent.getTicketsSold();
            bookedEvent.setTicketsSold(numTicketsSold + numTickets);
            bookedEvent.setTicketsLeft();
        }
        else{
            //Giving back
            Event bookedEvent = eventList.getFirst();
            int numTicketsSold = bookedEvent.getTicketsSold();
            bookedEvent.setTicketsSold(numTicketsSold - numTickets);
            bookedEvent.setTicketsLeft();
        }
    }
    public double getTotalPrice() {
        return unitPrice * numTickets;
    }
    public void setNumTickets(int numTickets){
        this.numTickets = numTickets;
    }
    public int getNumTickets(){
        return numTickets;
    }
    public String getEventName(){
        return eventName;
    }
    public String getDay(){
        return dayChosen;
    }
}
