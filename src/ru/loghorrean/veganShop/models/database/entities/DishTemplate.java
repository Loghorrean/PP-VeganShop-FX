package ru.loghorrean.veganShop.models.database.entities;

import ru.loghorrean.veganShop.exceptions.DatabaseException;

import java.util.HashSet;
import java.util.Set;

public class DishTemplate extends DatabaseEntity {
    private String name ;
    private String description;
    private Set<ProductCategory> categories;

    public DishTemplate(int id, String name, String description) {
        super(id);
        this.name = name;
        this.description = description;
        categories = new HashSet<>();
    }

    public DishTemplate(String name, String description) {
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

    public void addCategory(ProductCategory category) {
        categories.add(category);
    }

    public void removeCategory(ProductCategory category) {
        categories.remove(category);
    }

    public Set<ProductCategory> getCategories() {
        return categories;
    }

    @Override
    public String toString() {
        return "DishTemplate{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
