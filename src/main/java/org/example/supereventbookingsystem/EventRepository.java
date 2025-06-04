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
}
