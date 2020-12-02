package ru.loghorrean.veganShop.models.database.entities;

public class City extends DatabaseEntity {
    private String name;

    public City(int id, String name) {
        super(id);
        setName(name);
    }

    public City(String name) {
        this(-1, name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
