package se.meetngreet.server;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

@Service
public class UserService {

    private static ArrayList<User> users = new ArrayList<>();
    private static HashSet<User> users2 = new HashSet<>();

    public static void newUser(User user) {
        //Date date = new Date(year, month, day);
        users.add(new User(user.getFirstName(), user.getLastName(), user.getDateOfBirth(),
                user.getGender().toString(), user.getEmail(), user.getRelationshipStatus().toString(), user.getOccupation().toString(),
                user.getPlaceOfBirth(), user.getPlaceOfResidence(), user.getDescription()));

    }

    public static void updateUser(Payload payload) { //int userID, Payload payload) {
        //TODO Lär bli annorlunda vid hantering mot databas? Beror på om det ska vara en paralell datastruktur.


        Iterator<User> iter = users.iterator();
        while(iter.hasNext()){
            User user = iter.next();
            if (user.getUserID() == payload.getId()){

                switch (payload.getType()){
                    case "firstName": user.setFirstName(payload.getData()); break;
                    case "lastName": user.setLastName(payload.getData()); break;
                    case "dateOfBirth": user.setDateOfBirth(payload.getData()); break;
                    case "gender": user.setGender(payload.getData().toUpperCase()); break; //Vid enum ligger omvandling i set-metod från String till enum i Userklassen.
                    case "email": user.setEmail(payload.getData()); break;
                    case "relationshipStatus": user.setRelationshipStatus(payload.getData().toUpperCase()); break;
                    case "occupation": user.setOccupation(payload.getData().toUpperCase()); break;
                    case "placeOfBirth": user.setPlaceOfBirth(payload.getData()); break;
                    case "placeOfResidence": user.setPlaceOfResidence(payload.getData()); break;
                    case "description": user.setDescription(payload.getData()); break;
                    default: break;
                }

            }
        }


        //User user = users.get(0);
        //TOdo ta ut user ur datastruktur.

        //user.setFirstName(payload.getData());
        //TODO: originalswitchen för databas.
        /*
        switch (payload.getType()){
            case "firstName": user.setFirstName(payload.getData()); break;
            case "lastName": user.setLastName(payload.getData()); break;
            case "dateOfBirth": user.setDateOfBirth(payload.getData()); break;
            case "gender": user.setGender(payload.getData()); break; //Vid enum ligger omvandling i set-metod från String till enum i Userklassen.
            case "email": user.setEmail(payload.getData()); break;
            case "relationshipStatus": user.setRelationshipStatus(payload.getData()); break;
            case "occupation": user.setOccupation(payload.getData()); break;
            case "placeOfBirth": user.setPlaceOfBirth(payload.getData()); break;
            case "placeOfResidence": user.setPlaceOfResidence(payload.getData()); break;
            case "description": user.setDescription(payload.getData()); break;
            default: break;
        }

        users.set(0, user);
        */


        /*users.add(new User(user.getFirstName(), user.getLastName(), user.getDateOfBirth(),
                user.getGender().toString(), user.getEmail(), user.getRelationshipStatus().toString(), user.getOccupation().toString(),
                user.getPlaceOfBirth(), user.getPlaceOfResidence(), user.getDescription())); */
    }

    public static ArrayList<User> getAllUsers(){
        return users;
    }


}
