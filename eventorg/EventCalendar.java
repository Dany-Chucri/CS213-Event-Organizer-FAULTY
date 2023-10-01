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

    public Event[] getEvents(){
        return events;
    }

    public int getNumEvents(){
        return numEvents;
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

    public static void main(String[] args){
        EventCalendar calendar = new EventCalendar();
        Date testDate1 = new Date("2/28/2024");
        if (!testDate1.isValid()){
            System.out.println("Invalid date!");
            return;
        }
        Contact testContact1 = new Contact(Department.ITI, "ITI@rutgers.edu");
        if (!testContact1.isValid()){
            System.out.println("Invalid contact!");
            return;
        }
        int duration1 = 30;
        if (duration1 < 30 || duration1 > 120){
            System.out.println("Invalid duration!");
            return;
        }
        Event testEvent1 = new Event(testDate1, Timeslot.AFTERNOON, Location.TIL232, testContact1, duration1);
        System.out.println(testEvent1);

        Date testDate2 = new Date("2/28/2024");
        if (!testDate2.isValid()){
            System.out.println("Invalid date!");
            return;
        }
        Contact testContact2 = new Contact(Department.CS, "CS@rutgers.edu");
        if (!testContact1.isValid()){
            System.out.println("Invalid contact!");
            return;
        }
        int duration2 = 30;
        if (duration2 < 30 || duration2 > 120){
            System.out.println("Invalid duration!");
            return;
        }
        Event testEvent2 = new Event(testDate1, Timeslot.EVENING, Location.HLL114, testContact2, duration2);
        System.out.println(testEvent2);

        calendar.add(testEvent1);
        calendar.add(testEvent2);

        System.out.println("Basic Print:");
        calendar.print();

        System.out.println("Campus Print:");
        calendar.printByCampus();
        System.out.println("Date Print:");
        calendar.printByDate();
        System.out.println("Department Print:");
        calendar.printByDepartment();
    }

}