package org.example.supereventbookingsystem;

import java.util.ArrayList;

public class EventRepository {
    private static ArrayList<Event> eventList = new ArrayList<>();

    public static void initEventList(ArrayList<Event> eventList){
        EventRepository.eventList = eventList;
    }
    public static ArrayList<Event> getAllEvents(){
        return eventList;
    }

    public static ArrayList<Event> getEventsByName(String name, ArrayList<Event> currentEvents){
        ArrayList<Event> eventsByName = new ArrayList<>();

        for(int i = 0; i < currentEvents.size(); i++){
            if(currentEvents.get(i).getEventName().equalsIgnoreCase(name)){
                eventsByName.add(currentEvents.get(i));
            }
        }
        return eventsByName;
    }
    public static ArrayList<Event> getEventsByDay(String day, ArrayList<Event> nameSortedEvents){
        ArrayList<Event> eventsByDay = new ArrayList<>();

        for(int i = 0; i < nameSortedEvents.size(); i++){
            if(nameSortedEvents.get(i).getDay().equalsIgnoreCase(day)) {
                eventsByDay.add(nameSortedEvents.get(i));
            }
        }
        return eventsByDay;
    }

    public static boolean seatCheck(int ticketQuantity, ArrayList<Event> filteredEvents){
        for(int i = 0; i < filteredEvents.size(); i++){
            if(filteredEvents.get(i).getTicketsLeft() >= ticketQuantity){
                System.out.println(filteredEvents.get(i).getTicketsLeft() + " left");
                return true;
            }
        }
        return false;
    }
}
