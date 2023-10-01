package eventorg;

public enum Department {
    BAIT("Business Analytics and Information Technology"),
    CS("Computer Science"),
    EE("Electrical Engineering"),
    ITI("Information Technology and Informatics"),
    MATH("Mathematics");


    private final String fullMajorName;

    Department(String fullMajorName) {
        this.fullMajorName = fullMajorName;
    }

    public String getFullMajorName(){
        return fullMajorName;
    }
}