package ru.loghorrean.veganShop.models.database.entities;

import java.util.HashSet;
import java.util.Set;

public class ProductCategory extends DatabaseEntity {
    private String name;
    private String description;
    private final Set<DishTemplate> templates;
    private final Set<Product> productsOfCategory;

    public ProductCategory(int id, String name, String description) {
        super(id);
        this.name = name;
        this.description = description;
        templates = new HashSet<>();
        productsOfCategory = new HashSet<>();
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

    public void addProduct(Product product) {
        productsOfCategory.add(product);
    }

    public void removeProduct(Product product) {
        productsOfCategory.remove(product);
    }

    public Set<Product> getProductsOfCategory() {
        return productsOfCategory;
    }

    @Override
    public String toString() {
        return name;
    }
}
