package ru.loghorrean.veganShop;

import ru.loghorrean.veganShop.models.database.entities.DishTemplate;
import ru.loghorrean.veganShop.models.database.entities.ProductInCustomDish;

import java.util.ArrayList;
import java.util.List;

public class CurrentCustomDish {
    private DishTemplate templateOfTheDish;
    private List<ProductInCustomDish> composition;

    public CurrentCustomDish(DishTemplate template, List<ProductInCustomDish> composition) {
        templateOfTheDish = template;
        this.composition = composition;
    }

    public CurrentCustomDish(DishTemplate template) {
        this(template, new ArrayList<>());
    }

    public void setTemplate(DishTemplate template) {
        this.templateOfTheDish = template;
        composition.clear();
    }

    public void addToComposition(ProductInCustomDish link) {

    }

    public void deleteFromComposition(ProductInCustomDish link) {

    }

    public void updateByProduct(ProductInCustomDish link) {

    }

    public List<ProductInCustomDish> getComposition() {
        return composition;
    }
}
