package ru.loghorrean.veganShop.models.database.managers;

import ru.loghorrean.veganShop.models.database.entities.CustomDish;

import java.sql.SQLException;
import java.util.List;

public class CustomDishesManager extends BaseManager<CustomDish> {
    public CustomDishesManager() throws SQLException {
        super();
    }

    @Override
    public List<CustomDish> getAll() throws SQLException {
        return null;
    }

    @Override
    public CustomDish getOne(int id) throws SQLException {
        return null;
    }

    @Override
    public void insert(CustomDish entity) throws SQLException {

    }

    @Override
    public void update(CustomDish entity) throws SQLException {

    }

    @Override
    public void delete(CustomDish entity) throws SQLException {

    }
}
