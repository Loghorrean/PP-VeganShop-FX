package ru.loghorrean.veganShop.models.database.entities;

import java.util.HashSet;
import java.util.Set;

public class DishTemplate extends DatabaseEntity {
    private String name ;
    private String description;
    private final Set<ProductCategory> categories;
    private final Set<CustomDish> dishesWithTemplate;

    public DishTemplate(int id, String name, String description) {
        super(id);
        this.name = name;
        this.description = description;
        categories = new HashSet<>();
        dishesWithTemplate = new HashSet<>();
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

    public void addCustomDish(CustomDish customDish) {
        dishesWithTemplate.add(customDish);
    }

    public void removeCustomDish(CustomDish customDish) {
        dishesWithTemplate.remove(customDish);
    }

    public Set<CustomDish> getDishesWithTemplate() {
        return dishesWithTemplate;
    }

    @Override
    public String toString() {
        return "DishTemplate{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
