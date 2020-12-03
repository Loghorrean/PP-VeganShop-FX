package ru.loghorrean.veganShop.models.database.entities;

public class CategoriesForTemplate extends DatabaseEntity {
    private int id;
    private ProductCategory category;
    private DishTemplate template;

    public CategoriesForTemplate(int id, ProductCategory category, DishTemplate template) {
        super(id);
        this.category = category;
        category.addTemplate(template);
        this.template = template;
        template.addCategory(category);

    }

    public CategoriesForTemplate(ProductCategory category, DishTemplate template){
        this(-1, category, template);
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
