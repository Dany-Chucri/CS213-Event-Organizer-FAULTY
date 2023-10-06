package src.eventorg;

import java.util.Scanner;

/**
 * User interface to process command line input for the Event Calendar.
 * Can process a single or multiple lines at once.
 * @author Dany Chucri, Madhur Nutulapati
 */
public class EventOrganizer {
    private EventCalendar calendar;

    /**
     * Instantiates the event organizer using Event Calendar.
     */
    public EventOrganizer(){
        calendar = new EventCalendar();
    }

    /**
     * Begins the reading of standard input, parsing and executing commands for the Event Organizer.
     */
    public void run(){
        Scanner S = new Scanner(System.in);
        System.out.println("Event Organizer running....");
        while(true){
            String fullCommand = S.nextLine().trim();//read + trim
            if(fullCommand.isEmpty())//blank line
                continue;
            String result = fullCommand .replaceAll("\s+", " ");
            String[] token = result.split(" ");
            String command = token[0];
            switch(command){
                case "A":
                    aCommand(token);
                    break;
                case "R":
                    rCommand(token);
                    break;
                case "P":
                    pCommand();
                    break;
                case "PE":
                    peCommand();
                    break;
                case "PC":
                    pcCommand();
                    break;
                case "PD":
                    pdCommand();
                    break;
                case "Q":
                    System.out.println("Event Organizer terminated.");
                    System.exit(0);
                default:
                    System.out.println(token[0] + " is an invalid command!");
            }
        }
    }

    /**
     * Command for adding an Event to the Event Calendar.
     * @param token An array of tokens from the command-line arguments.
     */
    private void aCommand(String[] token){
        if(token.length!=7){
            System.out.println("Invalid command format.");
            return;
        }
        try{
            Date dateInput = createDate(token[1]);
            Timeslot startTime = createTimeSlot(token[2]);
            Location locationInput = createLocation(token[3]);
            Contact aContact = createContact(token[4],token[5]);
            int duration=Integer.parseInt(token[6]);

            if (duration < 30 || duration > 120 ){
                System.out.println("Event duration must be at least 30 minutes and at most 120 minutes");
                return;
            }
            if(dateInput == null || startTime == null || locationInput == null
                    || aContact == null){
                //System.out.println("Invalid tokens");
                return;
            }

            Event addEvent = new Event(dateInput,startTime,locationInput,aContact,duration);

            if(calendar.add(addEvent)){
                System.out.println("Event added to the calendar.");
            }else{
                System.out.println("The event is already on the calendar.");
            }
        }catch (Exception e){
            System.out.println("Error with adding an event.");
        }
    }

    /**
     * Instantiates a Date object to be used for the creation of an Event.
     * Performs error checks on the validity of a date.
     * @param date A String token in the form of "xx/xx/xxxx".
     * @return The Date object to be used.
     */
    private Date createDate(String date){
        Date addDate = new Date(date);
        if(addDate.isValid() == Date.INVALID_DATE){
            System.out.println(date + ": Invalid calendar date!");
            return null;
        } else if (addDate.isValid() == Date.PAST_DATE) {
            System.out.println(date + ": Event date must be a future date!");
            return null;
        } else if (addDate.isValid() == Date.TOO_DISTANT_DATE) {
            System.out.println(date + ": Event date must be within 6 months!");
            return null;
        }
        return addDate;
    }

    /**
     * Converts a string representing a time slot to its respective Enum representation.
     * Performs error checks on the validity of the time slot.
     * @param timeSlot A string representing the timeslot, case-insensitive.
     * @return A Timeslot enum to create an Event object with.
     */
    private Timeslot createTimeSlot(String timeSlot){
        if(!(timeSlot.equalsIgnoreCase("MORNING") || timeSlot.equalsIgnoreCase("AFTERNOON")
                || timeSlot.equalsIgnoreCase("EVENING"))){
            System.out.println("Invalid time slot!");
            return null;
        }
        timeSlot = timeSlot.toUpperCase();
        Timeslot startTime = Timeslot.valueOf(timeSlot);
        return startTime;
    }

    /**
     * Converts a string representing a location to its respective Enum representation.
     * Performs error checks on the validity of the location.
     * @param location A string representing the location, case-insensitive.
     * @return A Location enum to create an Event object with.
     */
    private Location createLocation(String location){
        if(!(location.equalsIgnoreCase("HLL114")||location.equalsIgnoreCase("ARC103")
                ||location.equalsIgnoreCase("BE_AUD")||location.equalsIgnoreCase("TIL232")
                ||location.equalsIgnoreCase("AB2225")||location.equalsIgnoreCase("MU302"))){
            System.out.println("Invalid location!");
            return null;
        }
        location = location.toUpperCase();
        try {
            return Location.valueOf(location);
        }catch (IllegalArgumentException e){
            System.err.println("Invalid location: " + location);
        }
        return null;
    }

    /**
     * Instantiates a Contact object to be used for the creation of an Event.
     * Performs error checks on the validity of a contact.
     * @param dept A String token in the form of "XX".
     * @param email A string token in the form of "XX@rutgers.edu".
     * @return The Contact object to be used.
     */
    private Contact createContact(String dept,String email){

        if(!(dept.equalsIgnoreCase("BAIT")||dept.equalsIgnoreCase("CS")
                ||dept.equalsIgnoreCase("ITI")||dept.equalsIgnoreCase("EE")
                ||dept.equalsIgnoreCase("MATH"))){
            System.out.println("Invalid contact information!");
            return null;
        }
        dept=dept.toUpperCase();
        Department newDept = Department.valueOf(dept);
        Contact addContact = new Contact(newDept,email);

        if(!addContact.isValid()){
            System.out.println("Invalid contact information!");
            return null;
        }
        return addContact;
    }

    /**
     * Command for removing an Event from the Event Calendar.
     * @param token An array of tokens from the command-line arguments.
     */
    private void rCommand(String[] token){
        if(token.length!=4){
            System.out.println("Invalid command format.");
            return;
        }
        try{
            Date dateInput = createDate(token[1]);
            if (dateInput == null) return;
            Timeslot startTime = createTimeSlot(token[2]);
            Location locationInput = createLocation(token[3]);
            Event eventToRemove = new Event(dateInput,startTime,locationInput,null,0);
            if(calendar.remove(eventToRemove)){
                System.out.println("Event has been removed from the calendar!");
            } else{
                System.out.println("Cannot remove; event is not in the calendar!");
            }
        }catch (Exception e){
            System.out.println("Error processing command");
        }
    }

    /**
     * Command for printing the event calendar as it currently is.
     */
    private void pCommand(){
        calendar.print();
    }

    /**
     * Command for printing the event calendar, with the events sorted by dates and then timeslots.
     */
    private void peCommand(){
        calendar.printByDate();
    }

    /**
     * Command for printing the event calendar, with the events sorted by campus and then building/room.
     */
    private void pcCommand(){
        calendar.printByCampus();
    }

    /**
     * Command for printing the event calendar, with the events sorted by the department in the contact.
     */
    private void pdCommand(){
        calendar.printByDepartment();
    }

    public static void main(String[] args){

    }
}
