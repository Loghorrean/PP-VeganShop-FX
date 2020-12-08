package ru.loghorrean.veganShop.models.database.managers;

import ru.loghorrean.veganShop.models.ProfileData;
import ru.loghorrean.veganShop.models.UsersData;
import ru.loghorrean.veganShop.models.database.entities.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdersManager extends BaseManager<Order> {
    public OrdersManager() throws SQLException {
        super();
    }

    @Override
    public List<Order> getAll() throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT * FROM orders";
            Statement s = c.createStatement();
            ResultSet set = s.executeQuery(sql);
            List<Order> orders = new ArrayList<>();
            while (set.next()) {
                orders.add(new Order.OrderBuilder()
                            .withId(set.getInt("order_id"))
                            .withUser(UsersData.getInstance().getUserById(set.getInt("user_id")))
                            .withPrice(set.getInt("order_price"))
                            .withPhone(set.getString("order_phone"))
                            .withCity(ProfileData.getInstance().getCityById(set.getInt("order_city")))
                            .withStreet(set.getString("order_street"))
                            .withHouse(set.getInt("order_house"))
                            .withFlat(set.getInt("order_flat"))
                            .withComment(set.getString("order_comment"))
                            .build());
            }
            return orders;
        }
    }

    @Override
    public Order getOne(int id) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT * FROM orders WHERE order_id = ?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, id);
            ResultSet set = s.executeQuery();
            if (set.next()) {
                return new Order.OrderBuilder()
                        .withId(set.getInt("order_id"))
                        .withUser(UsersData.getInstance().getUserById(set.getInt("user_id")))
                        .withPrice(set.getInt("order_price"))
                        .withPhone(set.getString("order_phone"))
                        .withCity(ProfileData.getInstance().getCityById(set.getInt("order_city")))
                        .withStreet(set.getString("order_street"))
                        .withHouse(set.getInt("order_house"))
                        .withFlat(set.getInt("order_flat"))
                        .withComment(set.getString("order_comment"))
                        .build();
            }
            return null;
        }
    }

    @Override
    public void insert(Order order) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "INSERT INTO orders (user_id, order_price, order_phone, order_city, order_street, order_house, " +
                    "order_flat, order_comment)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement s = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            s.setInt(1, order.getUser().getId());
            s.setInt(2, order.getPrice());
            s.setString(3, order.getPhone());
            s.setInt(4, order.getCity().getId());
            s.setString(5, order.getStreet());
            s.setInt(6, order.getHouse());
            s.setInt(7, order.getFlat());
            s.setString(8, order.getComment());
            s.executeUpdate();
            ResultSet set = s.getGeneratedKeys();
            if (set.next()) {
                order.setId(set.getInt(1));
                return;
            }
            throw new SQLException("ERROR WHILE INSERTING ORDER");
        }
    }

    @Override
    public void update(Order order) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "UPDATE orders SET user_id = ?, order_price = ?, order_phone = ?, order_city = ?, " +
                    "order_street = ?, order_house = ?, order_flat = ?, order_comment = ? WHERE order_id = ?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, order.getUser().getId());
            s.setInt(2, order.getPrice());
            s.setString(3, order.getPhone());
            s.setInt(4, order.getCity().getId());
            s.setString(5, order.getStreet());
            s.setInt(6, order.getHouse());
            s.setInt(7, order.getFlat());
            s.setString(8, order.getComment());
            s.setInt(9, order.getId());
            if (s.executeUpdate() == 1) {
                return;
            }
            throw new SQLException("ERROR WHILE UPDATING ORDER");
        }
    }

    @Override
    public void delete(Order order) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "DELETE FROM orders WHERE order_id = ?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, order.getId());
            if (s.executeUpdate() == 1) {
                order.deleteOrder();
                return;
            }
            throw new SQLException("ERROR WHILE DELETING ORDER");
        }
    }
}
