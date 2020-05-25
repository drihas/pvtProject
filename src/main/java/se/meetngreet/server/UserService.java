package se.meetngreet.server;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class UserService {

    private static ArrayList<User> users = new ArrayList<>();

    public static void newUser(User user) {
        //Date date = new Date(year, month, day);
        users.add(new User(user.getFirstName(), user.getLastName(), user.getDateOfBirth(),
                user.getGender().toString(), user.getEmail(), user.getRelationshipStatus().toString(), user.getOccupation().toString(),
                user.getPlaceOfBirth(), user.getPlaceOfResidence(), user.getDescription()));

    }

    public static ArrayList<User> getAllUsers(){
        return users;
    }


}
