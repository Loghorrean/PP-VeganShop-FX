package ru.loghorrean.veganShop.models.database.managers;

import ru.loghorrean.veganShop.models.database.entities.GeneralDish;

import java.sql.SQLException;
import java.util.List;

public class GeneralDishesManager extends BaseManager<GeneralDish> {
    public GeneralDishesManager() throws SQLException {
        super();
    }

    @Override
    public List<GeneralDish> getAll() throws SQLException {
        return null;
    }

    @Override
    public GeneralDish getOne(int id) throws SQLException {
        return null;
    }

    @Override
    public void insert(GeneralDish entity) throws SQLException {

    }

    @Override
    public void update(GeneralDish entity) throws SQLException {

    }

    @Override
    public void delete(GeneralDish entity) throws SQLException {

    }
}
