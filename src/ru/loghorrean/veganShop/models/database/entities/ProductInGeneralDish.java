package ru.loghorrean.veganShop.models.database.entities;

public class ProductInGeneralDish extends DatabaseEntity {
    private int id;
    private GeneralDish dish;
    private Product product;
    private float amount;

    public ProductInGeneralDish(int id, GeneralDish dish, Product product, float amount) {
        this.id = id;
        this.dish = dish;
        this.product = product;
        this.amount = amount;
    }

    public ProductInGeneralDish(GeneralDish dish, Product product, float amount) {
        this(-1, dish, product, amount);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GeneralDish getDish() {
        return dish;
    }

    public void setDish(GeneralDish dish) {
        this.dish = dish;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
