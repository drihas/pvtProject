package se.meetngreet.server;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.SQLException;
import java.util.Random;

public class User {

    enum Gender {
        MALE, FEMALE, NONBINARY
    }
    enum RelationshipStatus {
        SINGLE, INRELATIONSHIP, MARRIED, PREFEREDNOTTOSAY
    }
    enum Occupation {
        WORKING, STUDYING, UNEMPLOYED, OTHER
    }

    private int userID;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private Gender gender;
    private String email;
    private RelationshipStatus relationshipStatus;
    private Occupation occupation;
    private String placeOfBirth;
    private String placeOfResidence;
    private String description;
    private String interests;


    // Constructor with interests.
    public User(@JsonProperty("firstName") String firstName, @JsonProperty("lastName")String lastName,
                @JsonProperty("dateOfBirth") String dateOfBirth, @JsonProperty("gender") String gender,
                @JsonProperty("email") String email, @JsonProperty("relationshipStatus") String relationshipStatus,
                @JsonProperty("occupation") String occupation, @JsonProperty("placeOfBirth") String placeOfBirth,
                @JsonProperty("placeOfResidence") String placeOfResidence, @JsonProperty("description") String description,
                @JsonProperty("interests") String interests) throws SQLException {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = Gender.valueOf(gender.toUpperCase());
        this.email = email;
        this.relationshipStatus = RelationshipStatus.valueOf(relationshipStatus.toUpperCase());
        this.occupation = Occupation.valueOf(occupation.toUpperCase());
        this.placeOfBirth = placeOfBirth;
        this.placeOfResidence = placeOfResidence;
        this.description = description;
        Random random = new Random();
        this.userID = random.nextInt(1000 * random.nextInt(1000));
        this.interests = interests;
    }

    private void createInterests(String interests) throws SQLException {
        String[] list = interests.split(",");
        for (String item : list) {
            UserService.addInterest(getUserID(), new Interest(item));
        }
    }
    //Getters and setters:
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = Gender.valueOf(gender);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RelationshipStatus getRelationshipStatus() {
        return relationshipStatus;
    }

    public void setRelationshipStatus(String relationshipStatus) {
        this.relationshipStatus = RelationshipStatus.valueOf(relationshipStatus);
    }

    public Occupation getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = Occupation.valueOf(occupation);
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getPlaceOfResidence() {
        return placeOfResidence;
    }

    public void setPlaceOfResidence(String placeOfResidence) {
        this.placeOfResidence = placeOfResidence;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInterests() {
        return interests;
    }
}
