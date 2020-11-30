package ru.loghorrean.veganShop.models.database.managers;

import ru.loghorrean.veganShop.models.database.entities.CustomDishInOrder;

import java.sql.SQLException;
import java.util.List;

public class CustomDishesInOrderManager extends BaseManager<CustomDishInOrder> {
    public CustomDishesInOrderManager() throws SQLException {
        super();
    }

    @Override
    public List<CustomDishInOrder> getAll() throws SQLException {
        return null;
    }

    @Override
    public CustomDishInOrder getOne(int id) throws SQLException {
        return null;
    }

    @Override
    public void insert(CustomDishInOrder entity) throws SQLException {

    }

    @Override
    public void update(CustomDishInOrder entity) throws SQLException {

    }

    @Override
    public void delete(CustomDishInOrder entity) throws SQLException {

    }
}
