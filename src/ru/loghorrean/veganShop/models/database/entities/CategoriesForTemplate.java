package ru.loghorrean.veganShop.models.database.entities;

public class CategoriesForTemplate {
    private int id;
    private ProductCategory category;
    private DishTemplate template;

    public CategoriesForTemplate(int id, ProductCategory category, DishTemplate template) {
        this.id = id;
        this.category = category;
        this.template = template;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public DishTemplate getTemplate() {
        return template;
    }

    public void setTemplate(DishTemplate template) {
        this.template = template;
    }
}
