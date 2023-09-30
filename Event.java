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
        return("[Event Date: "+this.date+"] [Start: "+ this.startTime+"] [End: "+ endtime() + "] @" +this.location + "[Contact: " +  this.contact + "]");
    }
    @Override
    public boolean equals (Object obj){
        if (obj instanceof Event){
            Event event = (Event) obj;
            return this.date.equals(event.date) && this.startTime.equals(event.startTime)
                    && this.location.equals(event.location) && this.contact.equals(event.contact) &&
                    this.duration == event.duration;
        }
        return false;
    }
    //calculation of the endtime
    private int endtime(){
        int startHr = startTime.getHour();
        int startMin = startTime.getMinute();

        int totalTime = (startHr*60) +startMin;
        int totalEnd = totalTime + this.duration;

        int endHr = totalEnd/60;
        int endMin = totalEnd %60;
    //fix this
        return endTime;
    }
    public static void main(String [] args){
        //testing
    }
}