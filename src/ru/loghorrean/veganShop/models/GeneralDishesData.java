package ru.loghorrean.veganShop.models;

import ru.loghorrean.veganShop.models.database.entities.GeneralDish;
import ru.loghorrean.veganShop.models.database.managers.BaseManager;
import ru.loghorrean.veganShop.models.database.managers.GeneralDishesManager;

import java.sql.SQLException;
import java.util.List;

public class GeneralDishesData {
    private static GeneralDishesData instance;
    private BaseManager<GeneralDish> manager;
    private List<GeneralDish> dishes;

    private GeneralDishesData() {
        try {
            manager = new GeneralDishesManager();
            setDishes();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static GeneralDishesData getInstance() {
        if (instance == null) {
            instance = new GeneralDishesData();
        }
        return instance;
    }

    public void setDishes() throws SQLException {
        dishes = manager.getAll();
    }

    public List<GeneralDish> getDishes() {
        return dishes;
    }

    public void addDish(GeneralDish dish) throws SQLException {
        manager.insert(dish);
        dishes.add(dish);
    }

    public void removeDish(GeneralDish dish) throws SQLException {
        manager.update(dish);
    }

    public void updateDish(GeneralDish dish) throws SQLException{
        manager.delete(dish);
        dishes.remove(dish);
    }

    public GeneralDish getDishByName(String name) {
        for (GeneralDish dish: dishes) {
            if (dish.getName().equals(name)) {
                return dish;
            }
        }
        return null;
    }
}
