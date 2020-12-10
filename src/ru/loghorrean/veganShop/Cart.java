package ru.loghorrean.veganShop;

import ru.loghorrean.veganShop.models.database.entities.CustomDish;
import ru.loghorrean.veganShop.models.database.entities.GeneralDish;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private static Cart instance;
    private final Map<GeneralDish, Integer> generalDishesInCart;
    private final Map<CustomDish, Integer> customDishesInCart;

    private Cart() {
        generalDishesInCart = new HashMap<>();
        customDishesInCart = new HashMap<>();
    }

    public static Cart getInstance() {
        if (instance == null) {
            instance = new Cart();
        }
        return instance;
    }

    public void addGeneralToCart(GeneralDish dish, int amount) {
        generalDishesInCart.put(dish, amount);
    }

    public void addGeneralToCart(GeneralDish dish) {
        addGeneralToCart(dish, 1);
    }

    public void updateGeneralInCart(GeneralDish dish, int amount) {
        generalDishesInCart.put(dish, amount);
    }

    public void deleteGeneralFromCart(GeneralDish dish) {
        generalDishesInCart.remove(dish);
    }

    public void addCustomToCart(CustomDish dish, int amount) {
        customDishesInCart.put(dish, amount);
    }

    public void addCustomToCart(CustomDish dish) {
        addCustomToCart(dish, 1);
    }

    public void updateCustomInCart(CustomDish dish, int amount) {
        customDishesInCart.put(dish, amount);
    }

    public void deleteCustomFromCart(CustomDish dish) {
        customDishesInCart.remove(dish);
    }

    public void unsetCart() {
        instance = null;
    }

    public Map<GeneralDish, Integer> getGeneralFromCart() {
        return generalDishesInCart;
    }

    public Map<CustomDish, Integer> getCustomFromCart() {
        return customDishesInCart;
    }
}
