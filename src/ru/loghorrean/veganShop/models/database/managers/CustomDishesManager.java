package ru.loghorrean.veganShop.models.database.managers;

import ru.loghorrean.veganShop.models.TemplatesData;
import ru.loghorrean.veganShop.models.UsersData;
import ru.loghorrean.veganShop.models.database.entities.CustomDish;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomDishesManager extends BaseManager<CustomDish> {
    public CustomDishesManager() throws SQLException {
        super();
    }

    @Override
    public List<CustomDish> getAll() throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT * FROM custom_dishes";
            Statement s = c.createStatement();
            ResultSet set = s.executeQuery(sql);
            List<CustomDish> dishes = new ArrayList<>();
            while (set.next()) {
                dishes.add(new CustomDish(
                        set.getInt("dish_id"),
                        TemplatesData.getInstance().getTemplateById(set.getInt("template_id")),
                        set.getString("dish_name"),
                        set.getString("dish_recipe"),
                        UsersData.getInstance().getUserById(set.getInt("created_by_user"))
                ));
            }
            return dishes;
        }
    }

    @Override
    public CustomDish getOne(int id) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT * FROM custom_dishes WHERE dish_id = ?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, id);
            ResultSet set = s.executeQuery();
            if (set.next()) {
                return new CustomDish(
                        set.getInt("dish_id"),
                        TemplatesData.getInstance().getTemplateById(set.getInt("template_id")),
                        set.getString("dish_name"),
                        set.getString("dish_recipe"),
                        UsersData.getInstance().getUserById(set.getInt("created_by_user"))
                );
            }
            return null;
        }
    }

    @Override
    public void insert(CustomDish dish) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "INSERT INTO custom_dishes (template_id, dish_name, dish_recipe, created_by_user) VALUES (?, ?, ?, ?)";
            PreparedStatement s = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            s.setInt(1, dish.getTemplate().getId());
            s.setString(2, dish.getName());
            s.setString(3, dish.getRecipe());
            s.setInt(4, dish.getUserCreated().getId());
            s.executeUpdate();
            ResultSet set = s.getGeneratedKeys();
            if (set.next()) {
                dish.setId(set.getInt(1));
                return;
            }
            throw new SQLException("ERROR WHILE INSERTING DISH " + dish.getName());
        }
    }

    @Override
    public void update(CustomDish dish) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "UPDATE custom_dishes SET template_id = ?, dish_name = ?, dish_recipe = ?, created_by_user = ? " +
                    "WHERE dish_id = ?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, dish.getTemplate().getId());
            s.setString(2, dish.getName());
            s.setString(3, dish.getRecipe());
            s.setInt(4, dish.getUserCreated().getId());
            s.setInt(5, dish.getId());
            if (s.executeUpdate() == 1) {
                return;
            }
            throw new SQLException("ERROR WHILE UPDATING CUSTOM DISH " + dish.getName());
        }
    }

    @Override
    public void delete(CustomDish dish) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "DELETE FROM custom_dishes WHERE dish_id = ?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, dish.getId());
            if (s.executeUpdate() == 1) {
                dish.deleteDish();
                return;
            }
            throw new SQLException("ERROR WHILE DELETING DISH " + dish.getName());
        }
    }
}
