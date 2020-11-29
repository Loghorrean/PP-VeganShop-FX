package ru.loghorrean.veganShop.models.database.entities;

public class CustomDish extends DatabaseEntity {
    private int id;
    private DishTemplate template;
    private String name;
    private String recipe;
    private User userCreated;

    public CustomDish(int id, DishTemplate template, String name, String recipe, User userCreated) {
        this.id = id;
        this.template = template;
        this.name = name;
        this.recipe = recipe;
        this.userCreated = userCreated;
    }

    public CustomDish(DishTemplate template, String name, String recipe, User userCreated) {
        this(-1, template, name, recipe, userCreated);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DishTemplate getTemplate() {
        return template;
    }

    public void setTemplate(DishTemplate template) {
        this.template = template;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public User getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(User userCreated) {
        this.userCreated = userCreated;
    }
}
