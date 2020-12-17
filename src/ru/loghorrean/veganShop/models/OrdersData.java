package ru.loghorrean.veganShop.models;

import ru.loghorrean.veganShop.models.database.entities.Order;
import ru.loghorrean.veganShop.models.database.managers.BaseManager;
import ru.loghorrean.veganShop.models.database.managers.OrdersManager;

import java.sql.SQLException;
import java.util.List;

public class OrdersData {
    private static OrdersData instance;
    private BaseManager<Order> manager;
    private List<Order> orders;

    private OrdersData() {
        try {
            manager = new OrdersManager();
            setOrders();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static OrdersData getInstance() {
        if (instance == null) {
            instance = new OrdersData();
        }
        return instance;
    }

    public void setOrders() throws SQLException {
        orders = manager.getAll();
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrderToModel(Order order) throws SQLException {
        manager.insert(order);
        orders.add(order);
    }

    public void updateOrderInModel(Order order) throws SQLException {
        manager.update(order);
    }

    public void deleteOrderInModel(Order order) throws SQLException {
        manager.delete(order);
        orders.remove(order);
    }

    public Order getOrderById(int id) {
        for(Order order: orders) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }
}
