package se.meetngreet.server;

public class Location {
    private String name;
    private String description;
    private String coordinates; //TODO: ändra till mer lämpad typ.

    public Location(String name, String description, String coordinates) {
        this.name = name;
        this.description = description;
        this.coordinates = coordinates;
    }

    //  Getters and setters.


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }
}
