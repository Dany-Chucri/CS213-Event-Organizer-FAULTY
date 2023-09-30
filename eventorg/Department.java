package eventorg;

public enum Department {
    CS("Computer Science"),
    EE("Electrical Engineering"),
    ITI("Information Technology and Informatics"),
    MATH("Mathematics"),
    BAIT("Business Analytics and Information Technology");

    private final String fullMajorName;
    Department(String fullMajorName) {
        this.fullMajorName = fullMajorName;
    }
    public String getFullMajorName(){
        return fullMajorName;
    }
}