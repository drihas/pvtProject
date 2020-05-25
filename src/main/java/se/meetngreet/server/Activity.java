package se.meetngreet.server;

import java.sql.Timestamp;

public class Activity {

    enum Gender {
        MALE, FEMALE, NONBINARY
    }

    enum Activities {
        FOODDRINK, SPORT, ANIMALS, PARENTHANG, FILM, EXPLORE, FITNESSMINDFULNESS, GAMING, MUSIC
    }

    private int activityId;
    private String name;
    private Timestamp time;
    private String description;
    private Activities categoryOfActivity;
    private int minAge;
    private int maxAge;
    private int groupSize;
    private Gender allowedGender;
    private String location;
    private String coordinates;


    // Constructor. TODO: uppdatera denna!
    public Activity(String name, String description, String categoryOfActivity) {
        this.name = name;
        this.description = description;

    }

    // Getters and setters.


    public int getActivityId() {
        return activityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Activities getCategoryOfActivity() {
        return categoryOfActivity;
    }

    public void setCategoryOfActivity(Activities categoryOfActivity) {
        this.categoryOfActivity = categoryOfActivity;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public int getGroupSize() {
        return groupSize;
    }

    public void setGroupSize(int groupSize) {
        this.groupSize = groupSize;
    }

    public Gender getAllowedGender() {
        return allowedGender;
    }

    public void setAllowedGender(Gender allowedGender) {
        this.allowedGender = allowedGender;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }
}

