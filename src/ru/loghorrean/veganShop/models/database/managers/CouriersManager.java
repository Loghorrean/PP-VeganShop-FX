package ru.loghorrean.veganShop.models.database.managers;

import ru.loghorrean.veganShop.models.database.entities.Courier;

import java.sql.SQLException;
import java.util.List;

public class CouriersManager extends BaseManager<Courier> {
    public CouriersManager() throws SQLException {
        super();
    }

    @Override
    public List<Courier> getAll() throws SQLException {
        return null;
    }

    @Override
    public Courier getOne(int id) throws SQLException {
        return null;
    }

    @Override
    public void insert(Courier entity) throws SQLException {

    }

    @Override
    public void update(Courier entity) throws SQLException {

    }

    @Override
    public void delete(Courier entity) throws SQLException {

    }
}
