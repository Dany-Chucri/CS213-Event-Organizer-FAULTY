package eventorg;

public class EventCalendar {
    private Event[] events; //the array holding the list of events
    private int numEvents; //current number of events in the array

    private static final int baseSize = 4;
    private static final int NOT_FOUND = -1;

    public EventCalendar() {
        events = new Event[baseSize];
        numEvents = 0;
    }

    private int find(Event event) {//search an event in the list
        for (int i = 0; i < numEvents; i++){
            if (events[i].equals(event)){
                return i;
            }
        }
        return NOT_FOUND;
    }

    private void grow() {//increase the capacity by 4
        Event[] moreEvents = new Event[events.length + 4];
        for (int i = 0; i < numEvents; i++){
            moreEvents[i] = events[i];
        }
        events = moreEvents;
    }

    public boolean add(Event event) {
        if (numEvents == 0){
            events[0] = event;
            numEvents++;
            return true;
        }
        if (numEvents == events.length)
            this.grow();
        events[numEvents] = event;
        numEvents++;
        return true;
    }

    public boolean remove(Event event) {
        int removeIndex = find(event);
        if (removeIndex == NOT_FOUND)
            return false;
        else{
            events[removeIndex] = null;
            for (int i = removeIndex; i < numEvents - 1; i++){
                events[i] = events[i+1];
            }
            numEvents -= 1;
            return true;
        }
        //return false;
    }

    public boolean contains(Event event) {
        for (int i = 0 ; i < numEvents; i++){
            if (events[i].equals(event)){
                return true;
            }
        }
        return false;
    }

    public void print() { //print the array as is
        for(int i = 0; i < numEvents; i++){
            System.out.println(events[i]);
        }
    }

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

    public void printByDate() {
        this.selectionSortDate();
        this.print();
    } //ordered by date and timeslot

    public void printByCampus() {
        this.selectionSortCampus();
        this.print();
    } //ordered by campus and building/room

    public void printByDepartment(){
        this.selectionSortDepartment();
        this.print();
    } //ordered by department
}