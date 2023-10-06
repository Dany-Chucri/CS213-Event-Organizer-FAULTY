package src.eventorg;

/**
 Enum class representing an event's timeslot/startTime.
 Contains hour and minute of the timeslot, in military time.
 @author Dany Chucri, Madhur Nutulapati
 */
public enum Timeslot {
    MORNING(10, 30),
    AFTERNOON(14, 0),
    EVENING(18, 30);

    private final int hour;
    private final int minute;

    /**
     Identifies an instance of Timeslot with a given hour and number.
     @param hour The Timeslot's hour.
     @param minute The Timeslot's minute.
     */
    Timeslot(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    /**
     Basic getter for a Timeslot's hour.
     @return An integer representing the hour.
     */
    public int getHour() {
        return hour;
    }

    /**
     Basic getter for a Timeslot's minute.
     @return An integer representing the minute.
     */
    public int getMinute() {
        return minute;
    }
}
