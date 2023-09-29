import java.util.Objects;
import java.util.Date;

public class Event implements Comparable<Event> {
    private Date date; //the event date
    private Timeslot startTime; //the starting time
    private Location location;
    private Contact contact; //include the department name and email
    private int duration; //in minutes
//>=30 <=120
    public Event(Date date, Timeslot startTime, Location location, Contact contact, int duration){
            this.date = date;
            this.startTime = startTime;
            this.location = location;
            this.contact = contact;
            this.duration = duration;
    }
    @Override
    public int compareTo(Event compare){
        int dCompare = this.date.compareTo(compare.date);
        if (dCompare !=0){
            return dCompare;
        }
        return this.startTime.compareTo(compare.startTime);
    }
    @Override
    public String toString(){
        return("[Event Date: "+date+"] [Start: "+ startTime+"] [End: "+ endtime() + "] @" +location + "[Contact: " +  contact + "]");
    }
    @Override
    public boolean equals (Object o){
        if (this == o)
            return true;
        if (o == null || getClass ()!= getClass())
            return false;
        Event event = (Event) o;
        return duration == event.duration && Objects.equals(date,event.date) && Objects.equals(startTime,event.startTime) && Objects.equals(location,event.location);
    }
    //calculation of the endtime
//    private int endtime(){
//       if (startTime.equals("2:00"))
//        return 1;
//    }
    public static void main(String [] args){
    }
}