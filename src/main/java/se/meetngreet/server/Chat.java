package se.meetngreet.server;

import java.lang.reflect.Array;

public class Chat {
    private Array chatMembers;

    //TODO: denna kanske är smidigare att göra på annat sätt. Kanske bättre att implementera i frontend? Påverkar historik? Kanske går att spara ner därifrån till DB?

    public Chat(Array chatMembers) {
        this.chatMembers = chatMembers;
    }
}
