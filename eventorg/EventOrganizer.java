package eventorg;

import java.util.Scanner;

public class EventOrganizer {
    private EventCalendar calendar;

    public EventOrganizer(){
        calendar = new EventCalendar();
    }
    public void run(){
        Scanner S = new Scanner(System.in);
        System.out.println("Event Organizer running....");
        while(true){
            String fullCommand=S.nextLine().trim();//read + trim
            if(fullCommand.isEmpty())//blank line
                continue;
            String[] token = fullCommand.split(" ");
            String command = token[0];
            switch(command){
                case "A":
                    aCommand(token);
                    break;
                case "R":
                    rCommand(token);
                    break;
                case "P":
                    pcCommand(token);
                    break;
                case "PE":
                    peCommand(token);
                    break;
                case "PC":
                    pcCommand(token);
                    break;
                case "PD":
                    pdCommand(token);
                    break;
                case "Q":
                    System.out.println("Event Organizer terminated.");
                    return;
                default:
                    System.out.println("Invalid Command");
            }
        }
    }
    private void aCommand(String[] token){
        if(token.length!=7){
            return;
        }
        try{
            String dateInput = token[1];
            String timeslotInput = token[2];
            String locationInput = token[3];
            String departInput=token[4];
            String departEmailInput = token[5];
            String duration = token[6];

            Event addEvent = new Event(dateInput,timeslotInput,locationInput,departInput+" "+departEmailInput,Integer.parseInt(duration));
            if(calendar.add(addEvent)){
                System.out.println("Event has been added");
            }else{
                System.out.println("Event has not been added");
            }
        }catch (Exception e){
                System.out.println("Error with adding an event");
        }
    }
    private void rCommand(String[] token){
        if(token.length!=4){
            System.out.println("Invalid command format.");
            return;
        }
        try{
            String dateInput = token[1];
            String timeslotInput= token[2];
            String locationInput = token[3];
            Event eventToRemove = new Event(dateInput,timeslotInput,locationInput,"",0);

            if(calendar.remove(eventToRemove)){
                System.out.println("Event removed");
            } else{
                System.out.println("Event not found in calendar list");
            }
        }catch (Exception e){
            System.out.println("Error processing command");
        }
    }
    private void pCommand(String[] token){
        calendar.print();
    }
    private void peCommand(String[] token){
        calendar.printByDate();
    }
    private void pcCommand(String[]token){
        calendar.printByCampus();
    }
    private void pdCommand(String[]token){
        calendar.printByDepartment();
    }

    public static void main(String[] args){
//        EventOrganizer organizer=new EventOrganizer(null);
//        organizer.run();
    }
}