package se.meetngreet.server;

public class Badge {

    private String name;
    private String picture; //TODO: ändra till bild på något vis.

    // Constructor
    public Badge(String name, String picture) {
        this.name = name;
        this.picture = picture;
    }


    // Getters and setters:
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
    
}
