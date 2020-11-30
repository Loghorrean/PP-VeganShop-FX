package ru.loghorrean.veganShop.models.database.managers;

import ru.loghorrean.veganShop.models.database.entities.GeneralDishInOrder;

import java.sql.SQLException;
import java.util.List;

public class GeneralDishesInOrderManager extends BaseManager<GeneralDishInOrder> {
    public GeneralDishesInOrderManager() throws SQLException {
        super();
    }

    @Override
    public List<GeneralDishInOrder> getAll() throws SQLException {
        return null;
    }

    @Override
    public GeneralDishInOrder getOne(int id) throws SQLException {
        return null;
    }

    @Override
    public void insert(GeneralDishInOrder entity) throws SQLException {

    }

    @Override
    public void update(GeneralDishInOrder entity) throws SQLException {

    }

    @Override
    public void delete(GeneralDishInOrder entity) throws SQLException {

    }
}
