package se.meetngreet.server;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Payload {

    private int id;
    private String type;
    private String data;

    public Payload(@JsonProperty("type") String type, @JsonProperty("data") String data, @JsonProperty("id") int id){
        this.type = type;
        this.data = data;
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public String getData() {
        return data;
    }

    public int getId() {
        return id;
    }
}
