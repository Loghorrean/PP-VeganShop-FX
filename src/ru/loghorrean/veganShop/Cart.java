package ru.loghorrean.veganShop;

import ru.loghorrean.veganShop.models.ProductsData;
import ru.loghorrean.veganShop.models.ProductsInCustomDishData;
import ru.loghorrean.veganShop.models.ProductsInGeneralDishesData;
import ru.loghorrean.veganShop.models.database.entities.*;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private static Cart instance;
    private final Map<GeneralDish, Integer> generalDishesInCart;
    private final Map<CustomDish, Integer> customDishesInCart;
    private float cartPrice = 0;

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
        cartPrice += calcGeneralPrice(dish, amount);
    }

    public void addGeneralToCart(GeneralDish dish) {
        addGeneralToCart(dish, 1);
    }

    public void updateGeneralInCart(GeneralDish dish, int newAmount) {
        int oldAmount = generalDishesInCart.get(dish);
        cartPrice -= calcGeneralPrice(dish, oldAmount);
        generalDishesInCart.put(dish, newAmount);
        cartPrice += calcGeneralPrice(dish, newAmount);
    }

    public void deleteGeneralFromCart(GeneralDish dish) {
        int oldAmount = generalDishesInCart.get(dish);
        generalDishesInCart.remove(dish);
        cartPrice -= calcGeneralPrice(dish, oldAmount);
    }

    public void addCustomToCart(CustomDish dish, int amount) {
        customDishesInCart.put(dish, amount);
        cartPrice += calcCustomPrice(dish, amount);
    }

    public void addCustomToCart(CustomDish dish) {
        addCustomToCart(dish, 1);
    }

    public void updateCustomInCart(CustomDish dish, int amount) {
        int oldAmount = customDishesInCart.get(dish);
        cartPrice -= calcCustomPrice(dish, oldAmount);
        customDishesInCart.put(dish, amount);
        cartPrice += calcCustomPrice(dish, amount);
    }

    public void deleteCustomFromCart(CustomDish dish) {
        int oldAmount = customDishesInCart.get(dish);
        customDishesInCart.remove(dish);
        cartPrice -= calcCustomPrice(dish, oldAmount);
    }

    public void unsetCart() {
        instance = null;
    }

    public float getCartPrice() {
        return cartPrice;
    }

    public Map<GeneralDish, Integer> getGeneralFromCart() {
        return generalDishesInCart;
    }

    public Map<CustomDish, Integer> getCustomFromCart() {
        return customDishesInCart;
    }

    public int getNumberOfItems() {
        return generalDishesInCart.size() + customDishesInCart.size();
    }

    private float calcGeneralPrice(GeneralDish dish, int amount) {
        float dishesCost = 0;
        dishesCost += dish.getProdCosts();
        for (Product product: dish.getProductsInDish()) {
            ProductInGeneralDish link = ProductsInGeneralDishesData.getInstance().getLink(product, dish);
            dishesCost += link.getAmount() * link.getProduct().getPrice();
        }
        dishesCost *= amount;
        return dishesCost;
    }

    private float calcCustomPrice(CustomDish dish, int amount) {
        float dishesCost = dish.getProducts().size() * 100;
        for (Product product: dish.getProducts()) {
            ProductInCustomDish link = ProductsInCustomDishData.getInstance().getLink(product, dish);
            dishesCost += link.getAmount() * link.getProduct().getPrice();
        }
        dishesCost *= amount;
        return dishesCost;
    }
}
