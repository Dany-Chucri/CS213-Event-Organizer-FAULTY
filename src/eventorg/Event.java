package src.eventorg;

/**
 An Event to be held at a given location.
 Information of the event includes a Date, startTime, Location, Contact, and duration.
 @author Dany Chucri, Madhur Nutulapati
 */
public class Event implements Comparable<Event> {
    private final Date date; //the event date
    private final Timeslot startTime; //the starting time
    private final Location location;
    private final Contact contact; //include the department name and email
    private final int duration; //in minutes

    /**
     Creates an instance of Event.
     @param date Must be an object of the Date class.
     @param startTime A Timeslot enum for MORNING, AFTERNOON, or EVENING.
     @param location A Location enum.
     @param contact Must be an object of the Contact class.
     @param duration An integer represented an Events duration, between 30 and 120, inclusive.
     */
    public Event(Date date, Timeslot startTime, Location location, Contact contact, int duration){
        this.date = date;
        this.startTime = startTime;
        this.location = location;
        this.contact = contact;
        this.duration = duration;
    }

    /**
     Basic getter for an Event's Date.
     @return A Date object representing the Event's department.
     */
    public Date getDate() {
        return date;
    }

    /**
     Basic getter for an Event's Timeslot.
     @return A Timeslot enum representing the Event's starting time.
     */
    public Timeslot getStartTime() {
        return startTime;
    }

    /**
     Basic getter for an Event's Location.
     @return A Location enum representing the Event's location.
     */
    public Location getLocation() {
        return location;
    }

    /**
     Basic getter for an Event's Contact.
     @return A Contact object representing the Event's contact information.
     */
    public Contact getContact() {
        return contact;
    }

    /**
     Basic getter for an Event's duration.
     @return An integer representing the Event's duration in minutes.
     */
    public int getDuration() {
        return duration;
    }

    /**
     * {@inheritDoc}
     * Compares 2 Event objects based on which has the earlier Date, and if equal, the earlier Timeslot.
     * @param otherEvent The Second event to be compared to.
     * @return An integer using the same criterion as Object's compareTo().
     */
    @Override
    public int compareTo(Event otherEvent){
        int dCompare = this.date.compareTo(otherEvent.date);
        if (dCompare != 0){
            return dCompare;
        }
        return this.startTime.compareTo(otherEvent.startTime);
    }

    /**
     * {@inheritDoc}
     * Represents an Event in written form.
     * @return A String such as "[Event Date: 12/31/2023] [Start: 6:30pm] [End: 8:30pm] @TIL232 (Tillett Hall, Livingston) [Contact: Mathematics, MATH@rutgers.edu]".
     */
    @Override
    public String toString(){
        int hr = this.startTime.getHour();
        String meridian = "";
        if (hr < 12) meridian = "am";
        else {
            meridian = "pm";
            if (hr > 12) hr -= 12;
        }
        String starting = hr + ":";
        if (this.startTime.getMinute() == 0){
            starting = starting + "00";
        }
        else starting = starting + this.startTime.getMinute();
        return("[Event Date: " + this.date.toString() + "] [Start: " + starting + meridian + "] [End: " + this.endTime() + "] @" + this.location + " (" + this.location.getBuildingAndCampus() + ") [Contact: " + this.contact.toString() + "]");
    }

    /**
     * {@inheritDoc}
     * Compares 2 Event objects based on if they have the same dates, timeslots, and location.
     * @param obj The Second event to be compared to.
     * @return True if the 2 events are equal, otherwise false.
     */
    @Override
    public boolean equals(Object obj){
        if (obj instanceof Event){
            Event event = (Event) obj;
            return event.date.compareTo(this.date) == 0 && event.startTime.equals(this.startTime)
                    && event.location.equals(this.location);
        }
        return false;
    }

    /**
     * Used to calculate when an event will end based on its startTime and duration.
     * @return A string in the form of "xx:xx".
     */
    private String endTime(){
        int startHr = startTime.getHour();
        int startMin = startTime.getMinute();

        int totalTime = (startHr * 60) + startMin;
        int totalEnd = totalTime + this.duration;

        int endHr = totalEnd / 60;
        int endMin = totalEnd % 60;

        String meridian = "";
        if (endHr < 12) meridian = "am";
        else {
            meridian = "pm";
            if (endHr > 12) endHr -= 12;
        }
        if (endMin < 10 && endMin > 0)
            return endHr + ":0" + endMin + meridian;
        if (endMin >= 10)
            return endHr + ":" + endMin + meridian;
        else return endHr + ":00" + meridian;

    }

    /**
     * Testbed main
     * @param args Command-line input arguments.
     */
    public static void main(String[] args){

        //Test case 1 - equal Events -> Pass
        System.out.println("Running Test Case 1");
        Event event1_1 = new Event (new Date("11/13/2023"), Timeslot.MORNING, Location.AB2225, new Contact(Department.CS, "CS.rutgers.edu"), 60);
        Event event1_2 = new Event (new Date("11/13/2023"), Timeslot.MORNING, Location.AB2225, new Contact(Department.CS, "CS.rutgers.edu"), 60);
        if (event1_1.equals(event1_2))
            System.out.println("Testcase 1 passed");
        else System.out.println("Testcase 1 failed");

        //Test case 2 - different durations -> Pass
        System.out.println("Running Test Case 2");
        Event event2_1 = new Event (new Date("11/13/2023"), Timeslot.MORNING, Location.AB2225, new Contact(Department.CS, "CS.rutgers.edu"), 90);
        Event event2_2 = new Event (new Date("11/13/2023"), Timeslot.MORNING, Location.AB2225, new Contact(Department.CS, "CS.rutgers.edu"), 60);
        if (event2_1.equals(event2_2))
            System.out.println("Testcase 2 passed");
        else System.out.println("Testcase 2 failed");

        //Test case 3 - different contacts -> Pass
        System.out.println("Running Test Case 3");
        Event event3_1 = new Event (new Date("11/13/2023"), Timeslot.MORNING, Location.AB2225, new Contact(Department.CS, "CS.rutgers.edu"), 60);
        Event event3_2 = new Event (new Date("11/13/2023"), Timeslot.MORNING, Location.AB2225, new Contact(Department.ITI, "ITI.rutgers.edu"), 60);
        if (event3_1.equals(event3_2))
            System.out.println("Testcase 3 passed");
        else System.out.println("Testcase 3 failed");

        //Test case 4 - different locations -> Fail
        System.out.println("Running Test Case 4");
        Event event4_1 = new Event (new Date("11/13/2023"), Timeslot.MORNING, Location.HLL114, new Contact(Department.CS, "CS.rutgers.edu"), 60);
        Event event4_2 = new Event (new Date("11/13/2023"), Timeslot.MORNING, Location.AB2225, new Contact(Department.CS, "CS.rutgers.edu"), 60);
        if (event4_1.equals(event4_2))
            System.out.println("Testcase 4 passed");
        else System.out.println("Testcase 4 failed");

        //Test case 5 - different time slots -> Fail
        System.out.println("Running Test Case 6");
        Event event5_1 = new Event (new Date("11/13/2023"), Timeslot.MORNING, Location.AB2225, new Contact(Department.CS, "CS.rutgers.edu"), 60);
        Event event5_2 = new Event (new Date("11/13/2023"), Timeslot.EVENING, Location.AB2225, new Contact(Department.CS, "CS.rutgers.edu"), 60);
        if (event5_1.equals(event5_2))
            System.out.println("Testcase 5 passed");
        else System.out.println("Testcase 5 failed");

        //Test case 1 - different dates -> Fail
        System.out.println("Running Test Case 6");
        Event event6_1 = new Event (new Date("01/24/2024"), Timeslot.MORNING, Location.AB2225, new Contact(Department.CS, "CS.rutgers.edu"), 60);
        Event event6_2 = new Event (new Date("11/13/2023"), Timeslot.MORNING, Location.AB2225, new Contact(Department.CS, "CS.rutgers.edu"), 60);
        if (event6_1.equals(event6_2))
            System.out.println("Testcase 6 passed");
        else System.out.println("Testcase 6 failed");

    }
}