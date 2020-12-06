package ru.loghorrean.veganShop.models.database.managers;

import ru.loghorrean.veganShop.models.database.entities.GeneralDish;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GeneralDishesManager extends BaseManager<GeneralDish> {
    public GeneralDishesManager() throws SQLException {
        super();
    }

    @Override
    public List<GeneralDish> getAll() throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT * FROM general_dishes";
            Statement s = c.createStatement();
            ResultSet set = s.executeQuery(sql);
            List<GeneralDish> generalDishes = new ArrayList<>();
            while (set.next()) {
                generalDishes.add(new GeneralDish(
                        set.getInt("dish_id"),
                        set.getString("dish_name"),
                        set.getString("dish_description"),
                        set.getInt("time_to_cook")
                ));
            }
            return generalDishes;
        }
    }

    @Override
    public GeneralDish getOne(int id) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT * FROM general_dishes WHERE dish_id = ?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, id);
            ResultSet set = s.executeQuery();
            if (set.next()) {
                return new GeneralDish(
                        set.getInt("dish_id"),
                        set.getString("dish_name"),
                        set.getString("dish_description"),
                        set.getInt("time_to_cook")
                );
            }
            return null;
        }
    }

    @Override
    public void insert(GeneralDish dish) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "INSERT INTO general_dishes (dish_name, dish_description, time_to_cook) VALUES (?, ?, ?)";
            PreparedStatement s = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            s.setString(1, dish.getName());
            s.setString(2, dish.getDescription());
            s.setInt(3, dish.getTimeToCook());
            s.executeUpdate();
            ResultSet set = s.getGeneratedKeys();
            if (set.next()) {
                dish.setId(set.getInt(1));
                return;
            }
            throw new SQLException("ERROR WHILE INSERTING GENERAL DISH");
        }
    }

    @Override
    public void update(GeneralDish dish) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "UPDATE general_dishes SET dish_name = ?, dish_description = ?, time_to_cook = ? WHERE dish_id= ?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setString(1, dish.getName());
            s.setString(2, dish.getDescription());
            s.setInt(3, dish.getTimeToCook());
            s.setInt(4, dish.getId());
            if (s.executeUpdate() == 1) {
                return;
            }
            throw new SQLException("ERROR WHILE UPDATING GENERAL DISH");
        }
    }

    @Override
    public void delete(GeneralDish dish) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "DELETE FROM general_dishes WHERE dish_id = ?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, dish.getId());
            if (s.executeUpdate() == 1) {
                return;
            }
            throw new SQLException("ERROR WHILE DELETING GENERAL DISH");

        }
    }
}
