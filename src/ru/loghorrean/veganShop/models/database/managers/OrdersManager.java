package ru.loghorrean.veganShop.models.database.managers;

import ru.loghorrean.veganShop.models.database.entities.Order;

import java.sql.SQLException;
import java.util.List;

public class OrdersManager extends BaseManager<Order> {
    public OrdersManager() throws SQLException {
        super();
    }

    @Override
    public List<Order> getAll() throws SQLException {
        return null;
    }

    @Override
    public Order getOne(int id) throws SQLException {
        return null;
    }

    @Override
    public void insert(Order entity) throws SQLException {

    }

    @Override
    public void update(Order entity) throws SQLException {

    }

    @Override
    public void delete(Order entity) throws SQLException {

    }
}
