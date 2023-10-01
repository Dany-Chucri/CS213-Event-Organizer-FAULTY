package eventorg;

public enum Location {
    ARC103("Allison Road Classroom, Busch"),
    HLL114("Hill Center, Busch"),
    AB2225("Academic Building, College Avenue"),
    MU302("Murray Hall, College Avenue"),
    BE_AUD("Beck Hall, Livingston"),
    TIL232("Tillett Hall, Livingston");

    private final String buildingAndCampus;

    Location(String buildingAndCampus) {
        this.buildingAndCampus = buildingAndCampus;
    }

    public String getBuildingAndCampus() {
        return buildingAndCampus;
    }
}
