package ru.loghorrean.veganShop.models.database.entities;

import java.util.HashSet;
import java.util.Set;

public class ProductCategory extends DatabaseEntity {
    private String name;
    private String description;
    private Set<DishTemplate> templates;

    public ProductCategory(int id, String name, String description) {
        super(id);
        this.name = name;
        this.description = description;
        templates = new HashSet<>();
    }

    public ProductCategory(String name, String description) {
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

    public void addTemplate(DishTemplate template) {
        templates.add(template);
    }

    public void removeTemplate(DishTemplate template) {
        templates.remove(template);
    }

    public Set<DishTemplate> getTemplates() {
        return templates;
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
