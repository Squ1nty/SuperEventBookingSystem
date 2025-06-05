package org.example.supereventbookingsystem;

import com.almasb.fxgl.core.collection.Array;

import java.util.ArrayList;

public class User{
    String username = "";
    ArrayList<BookedEvent> bookedEvents = new ArrayList<>();

    public User(){}

    public User(String username, ArrayList<BookedEvent> bookedEvents){
        this.username = username;
    }
    public String getUsername() {
        return username;
    }

    public void addToBookedItems(BookedEvent bookedEvent){
        bookedEvents.add(bookedEvent);
    }
    public ArrayList<BookedEvent> getAllBookedEvents(){
        return bookedEvents;
    }
    public void clearBookedEvents() {
        bookedEvents.clear();
    }

    public double getTotalCartPrice() {
        return bookedEvents.stream()
                .mapToDouble(BookedEvent::getTotalPrice)
                .sum();
    }
}
