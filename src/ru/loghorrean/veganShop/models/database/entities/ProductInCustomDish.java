package ru.loghorrean.veganShop.models.database.entities;

public class ProductInCustomDish extends DatabaseEntity {
    private Product product;
    private CustomDish dish;
    private int amount;
    private String recipe;

    public ProductInCustomDish(int id, Product product, CustomDish dish, int amount, String recipe) {
        super(id);
        this.product = product;
        product.addCustomDish(dish);
        this.dish = dish;
        dish.addProduct(product);
        this.amount = amount;
        this.recipe = recipe;
    }

    public ProductInCustomDish(Product product, CustomDish dish, int amount, String recipe) {
        this(-1, product, dish, amount, recipe);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product.removeCustomDish(dish);
        this.product = product;
        this.product.addCustomDish(dish);
    }

    public CustomDish getDish() {
        return dish;
    }

    public void setDish(CustomDish dish) {
        this.dish.removeProduct(product);
        this.dish = dish;
        this.dish.addProduct(product);
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public void destroyLink() {
        dish.removeProduct(product);
        product.removeCustomDish(dish);
    }
}
