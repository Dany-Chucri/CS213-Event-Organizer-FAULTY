package eventorg;


import java.util.Scanner;

public class Event implements Comparable<Event> {
    private final Date date; //the event date
    private final Timeslot startTime; //the starting time
    private final Location location;
    private final Contact contact; //include the department name and email
    private final int duration; //in minutes

//>=30 <=120
    public Event(Date date, Timeslot startTime, Location location, Contact contact, int duration){
            this.date = date;
            this.startTime = startTime;
            this.location = location;
            this.contact = contact;
            this.duration = duration;
    }

    public Date getDate() {
        return date;
    }

    public Timeslot getStartTime() {
        return startTime;
    }

    public Location getLocation() {
        return location;
    }

    public Contact getContact() {
        return contact;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public int compareTo(Event compare){
        int dCompare = this.date.compareTo(compare.date);
        if (dCompare != 0){
            return dCompare;
        }
        return this.startTime.compareTo(compare.startTime);
    }

    @Override
    public String toString(){
        String starting = this.startTime.getHour() + ":";
        if (this.startTime.getMinute() == 0){
            starting = starting + "00";
        }
        else starting = starting + this.startTime.getMinute();
        return("[Event Date: " + this.date.toString() + "] [Start: " + starting + "] [End: " + this.endTime() + "] @" + this.location + " (" + this.location.getBuildingAndCampus() + ") [Contact: " + this.contact.toString() + "]");
    }

    @Override
    public boolean equals(Object obj){
        if (obj instanceof Event event){
            return this.date.equals(event.date) && this.startTime.equals(event.startTime)
                    && this.location.equals(event.location);
        }
        return false;
    }

    //calculation of the endtime
    private String endTime(){
        int startHr = startTime.getHour();
        int startMin = startTime.getMinute();

        int totalTime = (startHr * 60) + startMin;
        int totalEnd = totalTime + this.duration;

        int endHr = totalEnd / 60;
        int endMin = totalEnd % 60;
    //fix this
        if (endMin < 10 && endMin > 0)
            return endHr + ":0" + endMin + "pm";
        if (endMin >= 10)
            return endHr + ":" + endMin + "pm";
        else return endHr + ":00pm";

    }
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