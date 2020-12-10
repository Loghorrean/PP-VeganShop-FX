package ru.loghorrean.veganShop.models.database.managers;

import ru.loghorrean.veganShop.models.GeneralDishesData;
import ru.loghorrean.veganShop.models.OrdersData;
import ru.loghorrean.veganShop.models.database.entities.GeneralDishInOrder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GeneralDishesInOrderManager extends BaseManager<GeneralDishInOrder> {
    public GeneralDishesInOrderManager() throws SQLException {
        super();
    }

    @Override
    public List<GeneralDishInOrder> getAll() throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT * FROM orderdish_general";
            Statement s = c.createStatement();
            ResultSet set = s.executeQuery(sql);
            List<GeneralDishInOrder> links = new ArrayList<>();
            while (set.next()) {
                links.add(new GeneralDishInOrder(
                        set.getInt("record_id"),
                        GeneralDishesData.getInstance().getDishById(set.getInt("dish_id")),
                        OrdersData.getInstance().getOrderById(set.getInt("order_id")),
                        set.getFloat("dish_amount")
                ));
            }
            return links;
        }
    }

    @Override
    public GeneralDishInOrder getOne(int id) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT * FROM orderdish_general WHERE record_id = ?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, id);
            ResultSet set = s.executeQuery();
            if (set.next()) {
                return new GeneralDishInOrder(
                        set.getInt("record_id"),
                        GeneralDishesData.getInstance().getDishById(set.getInt("dish_id")),
                        OrdersData.getInstance().getOrderById(set.getInt("order_id")),
                        set.getFloat("dish_amount")
                );
            }
            return null;
        }
    }

    @Override
    public void insert(GeneralDishInOrder link) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "INSERT INTO orderdish_general (dish_id, order_id, dish_amount) VALUES (?, ?, ?)";
            PreparedStatement s = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            s.setInt(1, link.getDish().getId());
            s.setInt(2, link.getOrder().getId());
            s.setFloat(3, link.getAmount());
            s.executeUpdate();
            ResultSet set = s.getGeneratedKeys();
            if (set.next()) {
                link.setId(set.getInt(1));
                return;
            }
            throw new SQLException("ERROR WHILE INSERTING LINK");
        }
    }

    @Override
    public void update(GeneralDishInOrder link) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "UPDATE orderdish_general SET dish_id = ?, order_id = ?, dish_amount = ? WHERE record_id = ?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, link.getDish().getId());
            s.setInt(2, link.getOrder().getId());
            s.setFloat(3, link.getAmount());
            s.setInt(4, link.getId());
            if (s.executeUpdate() == 1) {
                return;
            }
            throw new SQLException("ERROR WHILE UPDATING LINK");
        }
    }

    @Override
    public void delete(GeneralDishInOrder link) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "DELETE FROM orderdish_general WHERE record_id = ?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, link.getId());
            if (s.executeUpdate() == 1) {
                link.destroyLink();
                return;
            }
            throw new SQLException("ERROR WHILE DELETING LINK");
        }
    }
}
