import java.util.Scanner;
public class EventOrganizer {
    private EventCalendar calendar;
    public EventOrganizer(){
        calendar = new EventCalendar();
    }
    public void run(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Event Organizer running....");

        while(true){

            String fullCommand=scanner.nextLine().trim();

            if(fullCommand.isEmpty())
                continue;

            switch(command){
                case "A":
                case "R":
                case "Q":
                    System.out.println("Event Organizer terminated.");
                    System.exit(0);
                default:
                    System.out.println("Invalid Command");
            }
        }
    }
    public static void main(String[] args){
        EventOrganizer organizer=new EventOrganizer();
    }
}