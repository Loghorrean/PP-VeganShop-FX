package ru.loghorrean.veganShop;

import ru.loghorrean.veganShop.models.database.entities.DishTemplate;
import ru.loghorrean.veganShop.models.database.entities.Product;

import java.util.HashSet;
import java.util.Set;

public class CurrentCustomDish {
    private static CurrentCustomDish instance;
    private DishTemplate templateOfTheDish;
    private Set<CompositionRow> composition;

//    public static CurrentCustomDish getInstance() {
//        if (instance == null) {
//            instance = new CurrentCustomDish();
//        }
//        return instance;
//    }

    public CurrentCustomDish() {
        composition = new HashSet<>();
    }

    public void setTemplate(DishTemplate template) {
        this.templateOfTheDish = template;
        composition.clear();
    }

    public DishTemplate getTemplate() {
        return templateOfTheDish;
    }

    public void addToComposition(Product product, int amount, String recipe) {
        composition.add(new CompositionRow(product, amount, recipe));
    }

    public void deleteFromComposition(Product product) {
        composition.removeIf(row -> row.getProduct() == product);
    }

    public void updateByProduct(Product product, int amount, String recipe) {
        for (CompositionRow row: composition) {
            if (row.getProduct() == product) {
                row.updateRow(product, amount, recipe);
                return;
            }
        }
    }

    private class CompositionRow {
        private Product product;
        private int amount;
        private String recipe;

        public CompositionRow(Product product, int amount, String recipe) {
            this.product = product;
            this.amount = amount;
            this.recipe = recipe;
        }

        public Product getProduct() {
            return product;
        }

        public int getAmount() {
            return amount;
        }

        public String getRecipe() {
            return recipe;
        }

        public void updateRow(Product product, int amount, String recipe) {
            this.product = product;
            this.amount = amount;
            this.recipe = recipe;
        }
    }
}
