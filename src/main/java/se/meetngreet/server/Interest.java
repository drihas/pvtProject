package se.meetngreet.server;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Interest {

    private String name;

    // Constructor.
    public Interest(@JsonProperty("name") String name) {
        this.name = name;
    }

    // Getter.
    public String getName() {
        return name;
    }
}
