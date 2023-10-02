package eventorg;

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
        String starting = this.startTime.getHour() + ":";
        if (this.startTime.getMinute() == 0){
            starting = starting + "00";
        }
        else starting = starting + this.startTime.getMinute();
        return("[Event Date: " + this.date.toString() + "] [Start: " + starting + "] [End: " + this.endTime() + "] @" + this.location + " (" + this.location.getBuildingAndCampus() + ") [Contact: " + this.contact.toString() + "]");
    }

    /**
     * {@inheritDoc}
     * Compares 2 Event objects based on if they have the same dates, timeslots, and location.
     * @param obj The Second event to be compared to.
     * @return True if the 2 events are equal, otherwise false.
     */
    @Override
    public boolean equals(Object obj){
        if (obj instanceof Event event){
            return this.date.equals(event.date) && this.startTime.equals(event.startTime)
                    && this.location.equals(event.location);
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

        if (endMin < 10 && endMin > 0)
            return endHr + ":0" + endMin + "pm";
        if (endMin >= 10)
            return endHr + ":" + endMin + "pm";
        else return endHr + ":00pm";

    }

    /**
     * Testbed main
     * @param args Command-line input arguments.
     */
    public static void main(String[] args){

        Date testDate1 = new Date("2/28/2024");
        if (!testDate1.isValid()){
            System.out.println("Invalid date!");
            return;
        }
        Contact testContact1 = new Contact(Department.BAIT, "BAIT@rutgers.edu");
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
        Contact testContact2 = new Contact(Department.BAIT, "BAIT@rutgers.edu");
        if (!testContact1.isValid()){
            System.out.println("Invalid contact!");
            return;
        }
        int duration2 = 30;
        if (duration2 < 30 || duration2 > 120){
            System.out.println("Invalid duration!");
            return;
        }
        Event testEvent2 = new Event(testDate1, Timeslot.MORNING, Location.TIL232, testContact2, duration2);
        System.out.println(testEvent2);

        if (testEvent1.equals(testEvent2)){
            System.out.println("equals() result: equal");
        }
        else System.out.println("equals() result: not equal");

        if ((testEvent1.compareTo(testEvent2)) == 0){
            System.out.println("compareTo() result: equal");
        }
        else System.out.println("compareTo() result: not equal");


    }
}