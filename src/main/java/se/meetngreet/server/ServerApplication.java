package se.meetngreet.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.AttributeView;
import java.sql.SQLException;
import java.util.ArrayList;

@SpringBootApplication
@RestController
public class ServerApplication extends SpringBootServletInitializer {

    //private ArrayList<User> users = new ArrayList<>();

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    // User
    @PutMapping("/user/add")
    public void addUser(@RequestBody User user) throws SQLException {
        //TODO: service.
        //users.add(new User(user.getFirstName(), user.getLastName(), user.getDateOfBirth()));
        UserService.newUser(user);
        //Todo: Ta emot interest. Kommaseparerad ista i Json verkar enkelt nog. Splitta, lägg in i array och iterera in i DB tillsammans med ID.
    }

    /*@PostMapping("/user/update2")
    public void updateUser(@RequestParam(value = "id", defaultValue = "0") int id, @RequestBody UserPayload payload) {
        if (id != 0) {
            //UserService.updateUser(id, payload);
        }
        //TODO send bad request.
    }*/

    @PutMapping("/user/update")
    public void updateUser(@RequestParam(value = "id", defaultValue = "0") int userID, @RequestBody UserPayload payload) throws SQLException {

        UserService.updateUser(userID, payload);

        //TODO send bad request.
    }

    @GetMapping("/user/get")
    public User getUser(@RequestParam(value = "id", defaultValue = "0") int userID) throws SQLException {
        return UserService.getUser(userID);
    }

    @GetMapping("/user/get/id")
    public int getUserId(@RequestParam(value = "email", defaultValue = "0") String email) throws SQLException {
        return UserService.getUserId(email);
    }

    @RequestMapping(value = "/user/delete", method = RequestMethod.DELETE)
    public @ResponseBody void deleteUser(@RequestParam(value = "id", defaultValue = "0") int userID) throws SQLException {
        UserService.deleteUser(userID);
    }

    @GetMapping("/user/getall")
    public ArrayList<User> getAll() throws SQLException {
        return UserService.getAllUsers();
    }

    @GetMapping("/user/activities/get")
    public ArrayList<Integer> getActivities(@RequestParam(value = "id", defaultValue = "0") int userID) throws SQLException {
        return UserService.getActivities(userID);
    }

    @PutMapping("/interest/add")
    public void addInterest(@RequestParam(value = "id", defaultValue = "0") int userID, @RequestBody Interest interest) throws SQLException {
        UserService.addInterest(userID, interest);
    }

    @GetMapping("/interest/get")
    public String getInterest(@RequestParam(value = "id", defaultValue = "0") int userID) throws SQLException {
        return UserService.getInterest(userID);
    }

    @DeleteMapping("/interest/add")
    public void deleteInterest(@RequestParam(value = "id", defaultValue = "0") int userID, @RequestBody Interest interest) throws SQLException {
        UserService.deleteInterest(userID, interest);
    }

    //Activity
    @PutMapping("/activity/add")
    public void addActivity(@RequestBody Activity activity) throws SQLException {
        ActivityService.addActivity(activity);
    }

    @PutMapping("/activity/update")
    public void updateActivity(@RequestParam(value = "id", defaultValue = "0") int activityID, @RequestBody ActivityPayload payload) throws SQLException {
        ActivityService.updateActivity(activityID, payload);
    }

    @RequestMapping(value = "/activity/delete", method = RequestMethod.DELETE)
    public @ResponseBody void deleteActivity (@RequestParam(value = "id", defaultValue = "0") int activityID) throws SQLException {
        ActivityService.deleteActivity(activityID);
    }

    @GetMapping("/activity/get")
    public Activity getACtivity(@RequestParam(value = "id", defaultValue = "0") int activityID) throws SQLException {
        return ActivityService.getActivity(activityID);
    }

    @GetMapping("/activity/getall")
    public ArrayList<Activity> getAllActivities() throws SQLException {
        return ActivityService.getAllActivities();
    }

    @PutMapping("/activity/participate")
    public void participate(@RequestParam(value = "user", defaultValue = "0") int userID, @RequestParam(value = "activity", defaultValue = "0") int activity_id) throws SQLException {
        ActivityService.participateActivity(userID, activity_id);
    }

    @DeleteMapping("/activity/unparticipate")
    public void unParticipate(@RequestParam(value = "user", defaultValue = "0") int userID, @RequestParam(value = "activity", defaultValue = "0") int activity_id) throws SQLException {
        ActivityService.unParticipateActivity(userID, activity_id);
    }

    @GetMapping("/activity/participants/get")
    public ArrayList<Integer> getParticipants(@RequestParam(value = "id", defaultValue = "0") int activityID) throws SQLException {
        return ActivityService.getParticipants(activityID);
    }


    @GetMapping("/")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }
}
