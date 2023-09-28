public class EventCalendar {
    private Event []events; //the array holding the list of events
    private int numEvents; //current number of events in the array

    private static final int arrSize = 10;
    private static final int NOT_FOUND = -1;
    public EventCalendar() {
        events = new Event[arrSize];
        numEvents = 0;
    }
    private int find(Event event) {//search an event in the list
        for (int i=0;i<numEvents;i++){
            if (events[i].equals(event)){
                return i;
            }
        }
        return NOT_FOUND;
    }
    private void grow() {//increase the capacity by 4
        Event[] moreEvents = new Event[events.length * 4];
        for (int i=0;i< numEvents;i++){
            moreEvents[i]=events[i];
        }
        events=moreEvents;
    }
    public boolean add(Event event) {
        if (numEvents==events.length)
            grow();
        events[numEvents++]= event;
        return true;
    }

    public boolean remove(Event event) {
        int removeIndex = find(event);
        if(removeIndex == NOT_FOUND)
            return false;
        else{
            for(int i=removeIndex;i<numEvents-1;i++){
                events[i] = events[i+1];
            }
            events[numEvents-1]=null;
            numEvents-=1;
            return true;
        }
        return false;
    }
    public boolean contains(Event event) {
        for (int i =0 ; i<numEvents;i++){
            if(events[i].equals(event)){
                return true;
            }
        }
        return false;
    }
    public void print() { //print the array as is
        for(int i=0;i<numEvents;i++){
            System.out.println(events[i]);
        }
    }
    public void sortingAlgo(){} //preferably bubble sort...
    public void printByDate() {
        print();
    } //ordered by date and timeslot
    public void printByCampus() {
        print();
    } //ordered by campus and building/room
    public void printByDepartment(){
        print();
    } //ordered by department
}