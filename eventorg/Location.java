package eventorg;

/**
 Enum class representing an event's Location.
 Can be one of 6 locations.
 @author Dany Chucri, Madhur Nutulapati
 */
public enum Location {
    ARC103("Allison Road Classroom, Busch"),
    HLL114("Hill Center, Busch"),
    AB2225("Academic Building, College Avenue"),
    MU302("Murray Hall, College Avenue"),
    BE_AUD("Beck Hall, Livingston"),
    TIL232("Tillett Hall, Livingston");

    private final String buildingAndCampus;

    /**
     Identifies an instance of Timeslot with a given building and campus
     @param buildingAndCampus The Timeslot's hour.
     */
    Location(String buildingAndCampus) {
        this.buildingAndCampus = buildingAndCampus;
    }

    /**
     Basic getter for a Location's building and campus.
     @return A String representing the building and campus specified by a location.
     */
    public String getBuildingAndCampus() {
        return buildingAndCampus;
    }
}
