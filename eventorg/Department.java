package eventorg;

/**
 Enum class representing an event's Department.
 Can be one of 5 department majors.
 @author Dany Chucri, Madhur Nutulapati
 */
public enum Department {
    BAIT("Business Analytics and Information Technology"),
    CS("Computer Science"),
    EE("Electrical Engineering"),
    ITI("Information Technology and Informatics"),
    MATH("Mathematics");

    private final String fullMajorName;

    /**
     Identifies an instance of Department with a major's name.
     @param fullMajorName A string representing the full name of a Department.
     */
    Department(String fullMajorName) {
        this.fullMajorName = fullMajorName;
    }

    /**
     Basic getter for a Department's full major name.
     @return A String representing the full major name.
     */
    public String getFullMajorName(){
        return fullMajorName;
    }
}