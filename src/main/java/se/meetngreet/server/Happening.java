package se.meetngreet.server;

public class Happening {
    private String name;
    private String description;
    private String categoryOfActivity; //TODO: borde kanske vara annat än just string. Enum?
    //TODO: UUID som unik identifierare. Samt variabler till protected?

    public Happening(String name, String description, String categoryOfActivity) {
        this.name = name;
        this.description = description;
        this.categoryOfActivity = categoryOfActivity;
    }

    // Getters and setters.

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryOfActivity() {
        return categoryOfActivity;
    }

    public void setCategoryOfActivity(String categoryOfActivity) {
        this.categoryOfActivity = categoryOfActivity;
    }
}
