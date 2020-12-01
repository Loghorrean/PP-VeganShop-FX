package ru.loghorrean.veganShop.models.database.entities;

import ru.loghorrean.veganShop.exceptions.DatabaseException;

public class CustomDish extends DatabaseEntity {
    private DishTemplate template = null;
    private String name = "";
    private String recipe = "";
    private User userCreated = null;

    public CustomDish(int id, DishTemplate template, String name, String recipe, User userCreated) throws DatabaseException {
        super(id);
        this.template = template;
        this.name = name;
        this.recipe = recipe;
        this.userCreated = userCreated;
    }

    public CustomDish(DishTemplate template, String name, String recipe, User userCreated) throws DatabaseException {
        this(-1, template, name, recipe, userCreated);
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
