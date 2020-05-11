package se.meetngreet.server;

import java.util.Date;
import java.util.UUID;

public class User {
    private UUID userID;
    private String firstName;
    private String lastName;
    private Date dateOfBirth; //TODO: ändra från int till bättre anpassad variabeltyp. Borde finnas en för födelsedatum eller datum.
    private String userName; //TODO: Använda som unik identifierare istället för ID-nummer eller e-post? EJ i klassdiagram. Kanske ha en UUID ändå.
    private String userType; //TODO: Omdöpt från klassdiagram. Ersätta egen klass bara för en userType? Enum istället för string? Känns onödigt med en klass eftersom det är antingenn new resident eller local. Enum istället för boolsk eftersom man kanske vill utöka längre fram.
    //TODO: Kan gender läggas som en variabel istället för en egen klass för att minska på databasen?

    // Constructor
    public User(String firstName, String lastName, Date dateOfBirth, String userName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.userName = userName;
    }

    //Getters:
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getUserName() {
        return userName;
    }

    public UUID getUserID() {
        return userID;
    }

    public String getUserType() {
        return userType;
    }

    //Setters:
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    // TODO
    public void createActivity(){
        //TODO: Borde nog ligga någon annanstans mer relaterat till REST-API ...
    }

    public void changeInterest(){
        // TODO:
    }

    public void terminateAccount(){
        //TODO: kanske också borde ligga mer relaterat till REST-API.
    }

    public void listActivities(){
        //TODO REST-API
        //Omdöpt från klassdiagrammet.
    }

    public void attendActivity(){
        // TODO
    }

    public void viewProfile(){
        //TODO
    }

    public void viewBadger(){
        //TODO
    }

    public void addProfilePicture(){
        //TODO
    }



}
