package se.meetngreet.server;

import java.util.Random;

public class Activity {

    enum Gender {
        MALE, FEMALE, NONBINARY, ALL
    }

    enum Activities {
        FOODDRINK, SPORT, ANIMALS, PARENTHANG, FILM, EXPLORE, FITNESSMINDFULNESS, GAMING, MUSIC
    }

    private int activityId;
    private String name;
    private String time;
    private String description;
    private Activities categoryOfActivity;
    private int minAge;
    private int maxAge;
    private int groupSize;
    private Gender allowedGender;
    private String location;
    private String coordinates;

    // Constructor.
    public Activity(String name, String time, String description, String categoryOfActivity, String minAge, String maxAge, String groupSize, String allowedGender, String location, String coordinates) {
        this.name = name;
        this.time = time;
        this.description = description;
        this.categoryOfActivity = Activities.valueOf(categoryOfActivity.toUpperCase());
        this.minAge = Integer.parseInt(minAge);
        this.maxAge = Integer.parseInt(maxAge);
        this.groupSize = Integer.parseInt(groupSize);
        this.allowedGender = Gender.valueOf(allowedGender);
        this.location = location;
        this.coordinates = coordinates;
        Random random = new Random();
        this.activityId = random.nextInt(1000 * random.nextInt(1000));
    }

    // Getters and setters.
    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
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

