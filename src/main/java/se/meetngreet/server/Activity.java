package se.meetngreet.server;

public class Activity extends Happening {

    private int ageRange; //TODO: ska ersättas med minAge och maxAge?
    private int groupSize;

    public Activity(String name, String description, String categoryOfActivity, int ageRange, int groupSize) {
        super(name, description, categoryOfActivity);
        this.ageRange = ageRange;
        this.groupSize = groupSize;
    }

    // Getters and setters.

    public int getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(int ageRange) {
        this.ageRange = ageRange;
    }

    public int getGroupSize() {
        return groupSize;
    }

    public void setGroupSize(int groupSize) {
        this.groupSize = groupSize;
    }
}
