package ru.loghorrean.veganShop.models.database.entities;

import ru.loghorrean.veganShop.exceptions.DatabaseException;

import java.util.Set;

public class DishTemplate extends DatabaseEntity {
    private String name = "";
    private String description = "";
    private Set<ProductCategory> categories;

    public DishTemplate(int id, String name, String description) throws DatabaseException {
        super(id);
        this.name = name;
        this.description = description;
    }

    public DishTemplate(String name, String description) throws DatabaseException {
        this(-1, name, description);
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
}
