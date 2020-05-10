package se.meetngreet.server;

public class Gender {
    private String genderType; //TODO: enums?

    public Gender(String genderType) {
        this.genderType = genderType;
    }

    // Getters and setters.

    public String getGenderType() {
        return genderType;
    }

    public void setGenderType(String genderType) {
        this.genderType = genderType;
    }
}
