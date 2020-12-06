package ru.loghorrean.veganShop.models;

import ru.loghorrean.veganShop.models.database.entities.CustomDish;
import ru.loghorrean.veganShop.models.database.managers.BaseManager;
import ru.loghorrean.veganShop.models.database.managers.CustomDishesManager;

import java.sql.SQLException;
import java.util.List;

public class CustomDishesData {
    private static CustomDishesData instance;
    private BaseManager<CustomDish> manager;
    private List<CustomDish> dishes;

    private CustomDishesData() {
        try {
            manager = new CustomDishesManager();
            setDishes();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static CustomDishesData getInstance() {
        if (instance == null) {
            instance = new CustomDishesData();
        }
        return instance;
    }

    public void setDishes() throws SQLException {
        dishes = manager.getAll();
    }

    public List<CustomDish> getDishes() {
        return dishes;
    }

    public void addDish(CustomDish dish) throws SQLException {
        manager.insert(dish);
        dishes.add(dish);
    }

    public void updateDish(CustomDish dish) throws SQLException {
        manager.update(dish);
    }

    public void removeDish(CustomDish dish) throws SQLException {
        manager.delete(dish);
        dishes.remove(dish);
    }

    public CustomDish getDishById(int id) {
        for (CustomDish dish: dishes) {
            if (dish.getId() == id) {
                return dish;
            }
        }
        return null;
    }

    public CustomDish getDishByName(String name) {
        for (CustomDish dish: dishes) {
            if (dish.getName().equals(name)) {
                return dish;
            }
        }
        return null;
    }
}
