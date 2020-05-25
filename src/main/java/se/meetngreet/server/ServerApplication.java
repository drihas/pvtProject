package se.meetngreet.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@SpringBootApplication
@RestController
public class ServerApplication extends SpringBootServletInitializer {

    //private ArrayList<User> users = new ArrayList<>();

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    @PostMapping
    public void addUser(@RequestBody User user){
        //TODO: service.
        //users.add(new User(user.getFirstName(), user.getLastName(), user.getDateOfBirth()));
        UserService.newUser(user);
    }

    @GetMapping("/user")
    public ArrayList<User> getAll(){
            return UserService.getAllUsers();
    }


    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/yo")
    public String yo(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Yo mah man, %s!", name);
    }

}
