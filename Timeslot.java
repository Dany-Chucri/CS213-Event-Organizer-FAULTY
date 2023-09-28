public enum Timeslot {
    MORNING(10, 30),
    AFTERNOON(14, 0),
    EVENING(18, 30);

    private int hour;
    private int minute;

    Timeslot(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }
}
