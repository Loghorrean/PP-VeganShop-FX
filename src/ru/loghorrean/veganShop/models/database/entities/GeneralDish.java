package ru.loghorrean.veganShop.models.database.entities;

public class GeneralDish extends DatabaseEntity {
    private int id;
    private String name;
    private String description;
    private int timeToCook;

    public GeneralDish(int id, String name, String description, int timeToCook) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.timeToCook = timeToCook;
    }

    public GeneralDish(String name, String description, int timeToCook) {
        this(-1, name, description, timeToCook);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getTimeToCook() {
        return timeToCook;
    }

    public void setTimeToCook(int timeToCook) {
        this.timeToCook = timeToCook;
    }
}
