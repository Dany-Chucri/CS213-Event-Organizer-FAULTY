package src.eventorg;

/**
 * Holds a list of Event objects to form an event calendar.
 * Contains an events array as well as a number of events tracker.
 * Size of the events array can increase if needed but not decrease.
 * @author Dany Chucri, Madhur Nutulapati
 */
public class EventCalendar {
    private Event[] events; //the array holding the list of events
    private int numEvents; //current number of events in the array

    private static final int baseSize = 4;
    private static final int NOT_FOUND = -1;

    /**
     Creates an instance of EventCalendar with a size of 0.
     */
    public EventCalendar() {
        events = new Event[baseSize];
        numEvents = 0;
    }

    /**
     Basic getter for the events array.
     @return An Event[] object representing the list of events.
     */
    public Event[] getEvents(){
        return events;
    }

    /**
     Basic getter for the EventCalendar's number of events.
     @return An integer representing the number of events.
     */
    public int getNumEvents(){
        return numEvents;
    }

    /**
     * Searches for a specified Event object in the list.
     * Uses the criterion specified in Event's equals() method.
     * @param event The Event to be searched for.
     * @return An integer representing the position of the Event in the EventCalendar.
     */
    private int find(Event event) {
        for (int i = 0; i < numEvents; i++){
            if (events[i].equals(event)){
                return i;
            }
        }
        return NOT_FOUND;
    }

    /**
     * Increases the capacity of the events array by adding 4 event slots.
     */
    private void grow() {
        Event[] moreEvents = new Event[events.length + 4];
        for (int i = 0; i < numEvents; i++){
            moreEvents[i] = events[i];
        }
        events = moreEvents;
    }

    /**
     * Adds a given Event Object to the events array of EventCalendar.
     * @param event The Event to be added.
     * @return True if the Event was added, otherwise false.
     */
    public boolean add(Event event) {
        if (numEvents == 0){
            events[0] = event;
            numEvents++;
            return true;
        }
        if (find(event) != -1){
            return false;
        }
        if (numEvents == events.length)
            this.grow();
        events[numEvents] = event;
        numEvents++;
        return true;
    }

    /**
     * Deletes a specified Event Object from the events list.
     * @param event The event to be deleted.
     * @return True if the Event was removed, otherwise false.
     */
    public boolean remove(Event event) {
        int removeIndex = find(event);
        if (removeIndex == NOT_FOUND){
            return false;
        }
        else{
            events[removeIndex] = null;
            for (int i = removeIndex; i < numEvents - 1; i++){
                events[i] = events[i+1];
            }
            numEvents -= 1;
            return true;
        }
    }

    /**
     * Checks if the specified Event is already in the EventCalendar.
     * @param event Event to be looked for.
     * @return True if the Event is in the EventCalendar, otherwise false.
     */
    public boolean contains(Event event) {
        for (int i = 0 ; i < numEvents; i++){
            if (events[i].equals(event)){
                return true;
            }
        }
        return false;
    }

    /**
     * Prints out the Events of the calendar as already listed.
     */
    public void print() { //print the array as is
        if (numEvents == 0){
            System.out.println("Event calendar is empty!");
            return;
        }
        System.out.println("* Event calendar *");
        for(int i = 0; i < numEvents; i++){
            System.out.println(events[i]);
        }
        System.out.println("* end of event calendar *");
    }

    /**
     * Used to Sort the EventCalendar by the earlier Date.
     * Utilizes a selection sort algorithm.
     */
    public void selectionSortDate(){
        for (int i = 0; i < numEvents - 1; i++){
            int min = i;
            for (int j = i + 1; j < numEvents; j++){
                if (events[j].getDate().compareTo(events[min].getDate()) == 0) {
                    if (events[j].getStartTime().ordinal() < events[min].getStartTime().ordinal()){
                        min = j;
                    }
                }
                else if (events[j].getDate().compareTo(events[min].getDate()) < 0){
                    min = j;
                }
            }
            Event minDateEvent = events[min];
            events[min] = events[i];
            events[i] = minDateEvent;
        }
    }

    /**
     * Used to sort the EventCalendar in alphabetical order of the Location of Events.
     * Utilizes a selection sort algorithm.
     */
    public void selectionSortCampus(){
        for (int i = 0; i < numEvents - 1; i++){
            int min = i;
            for (int j = i + 1; j < numEvents; j++){
                if (events[j].getLocation().ordinal() < events[min].getLocation().ordinal()){
                    min = j;
                }
            }
            Event minCampusEvent = events[min];
            events[min] = events[i];
            events[i] = minCampusEvent;
        }
    }

    /**
     * Used to sort the EventCalendar in alphabetical order of the Department of Events.
     * Utilizes a selection sort algorithm.
     */
    public void selectionSortDepartment(){
        for (int i = 0; i < numEvents - 1; i++){
            int min = i;
            for (int j = i + 1; j < numEvents; j++){
                if (events[j].getContact().getDepartment().ordinal() < events[min].getContact().getDepartment().ordinal()){
                    min = j;
                }
            }
            Event minDepartmentEvent = events[min];
            events[min] = events[i];
            events[i] = minDepartmentEvent;
        }
    }

    /**
     * Prints out the events of the calendar, sorted by their dates and timeslots.
     */
    public void printByDate() {
        this.selectionSortDate();
        if (numEvents == 0){
            System.out.println("Event calendar is empty!");
            return;
        }
        System.out.println("* Event calendar by event date and start time *");
        for(int i = 0; i < numEvents; i++){
            System.out.println(events[i]);
        }
        System.out.println("* end of event calendar *");
    }

    /**
     * Prints out the events of the calendar, sorted by their campus and building.
     */
    public void printByCampus() {
        this.selectionSortCampus();
        if (numEvents == 0){
            System.out.println("Event calendar is empty!");
            return;
        }
        System.out.println("* Event calendar by campus and building *");
        for(int i = 0; i < numEvents; i++){
            System.out.println(events[i]);
        }
        System.out.println("* end of event calendar *");
    }

    /**
     * Prints out the events of the calendar, sorted by their department.
     */
    public void printByDepartment(){
        this.selectionSortDepartment();
        if (numEvents == 0){
            System.out.println("Event calendar is empty!");
            return;
        }
        System.out.println("* Event calendar by department *");
        for(int i = 0; i < numEvents; i++){
            System.out.println(events[i]);
        }
        System.out.println("* end of event calendar *");
    }

    public static void main(String[] args) {

    }

}