package ru.loghorrean.veganShop.models.database.entities;

public class Product extends DatabaseEntity {
    private String name;
    private String description;
    private float amount;
    private int price;
    private int calories;
    private boolean isAllergic;
    private ProductCategory category;

    private Product(int id) {
        super(id);
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

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public boolean isAllergic() {
        return isAllergic;
    }

    public void setAllergic(boolean allergic) {
        isAllergic = allergic;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public static class ProductBuilder {
        private Product product;

        public ProductBuilder() {
            product = new Product(-1);
        }

        public ProductBuilder withId(int id) {
            product.id = id;
            return this;
        }

        public ProductBuilder withName(String name) {
            product.name = name;
            return this;
        }

        public ProductBuilder withDescription(String description) {
            product.description = description;
            return this;
        }

        public ProductBuilder withAmount(float amount) {
            product.amount = amount;
            return this;
        }

        public ProductBuilder withPrice(int price) {
            product.price = price;
            return this;
        }

        public ProductBuilder withCalories(int calories) {
            product.calories = calories;
            return this;
        }

        public ProductBuilder withAllergic(boolean isAllergic) {
            product.isAllergic = isAllergic;
            return this;
        }

        public ProductBuilder withCategory(ProductCategory category) {
            product.category = category;
            category.addProduct(product);
            return this;
        }

        public Product build() {
            System.out.println(product);
            return product;
        }
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                ", calories=" + calories +
                ", isAllergic=" + isAllergic +
                ", category=" + category +
                '}';
    }
}
