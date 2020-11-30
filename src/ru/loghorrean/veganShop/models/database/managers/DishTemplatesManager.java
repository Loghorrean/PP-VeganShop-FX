package ru.loghorrean.veganShop.models.database.managers;

import ru.loghorrean.veganShop.models.database.entities.DishTemplate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DishTemplatesManager extends BaseManager<DishTemplate> {
    public DishTemplatesManager() throws SQLException {
        super();
    }

    @Override
    public List<DishTemplate> getAll() throws SQLException {
        try(Connection c = database.getConnection()) {
            String sql = "SELECT * FROM dish_templates";
            Statement s = c.createStatement();
            ResultSet set = s.executeQuery(sql);
            List<DishTemplate> templates = new ArrayList<>();
            while(set.next()) {
                templates.add(new DishTemplate(
                        set.getInt("template_id"),
                        set.getString("template_name"),
                        set.getString("template_description")
                ));
            }
            return templates;
        }
    }

    @Override
    public DishTemplate getOne(int id) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT * FROM dish_templates WHERE template_id = ?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, id);
            ResultSet set = s.executeQuery();
            if (set.next()) {
                return new DishTemplate(
                        set.getInt("template_id"),
                        set.getString("template_name"),
                        set.getString("template_description")
                );
            }
            return null;
        }
    }

    public DishTemplate getTemplateByName(String name) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT * FROM dish_templates WHERE template_name = ?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setString(1, name);
            ResultSet set = s.executeQuery();
            if (set.next()) {
                return new DishTemplate(
                        set.getInt("template_id"),
                        set.getString("template_name"),
                        set.getString("template_description")
                );
            }
            return null;
        }
    }

    @Override
    public void insert(DishTemplate template) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "INSERT INTO dish_templates (template_name, template_description) VALUES (?, ?)";
            PreparedStatement s = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            s.setString(1, template.getName());
            s.setString(2, template.getDescription());
            s.executeUpdate();
            ResultSet set = s.getGeneratedKeys();
            if (set.next()) {
                template.setId(set.getInt(1));
                return;
            }
            throw new SQLException("ERROR WHILE ADDING TEMPLATE " + template.getName());
        }
    }

    @Override
    public void update(DishTemplate template) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "UPDATE dish_templates SET template_name = ?, template_description = ? WHERE template_id = ?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setString(1, template.getName());
            s.setString(2, template.getDescription());
            s.setInt(3, template.getId());
            if (s.executeUpdate() == 1) {
                return;
            }
            throw new SQLException("ERROR WHILE UPDATING TEMPLATE " + template.getName());
        }
    }

    @Override
    public void delete(DishTemplate template) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "DELETE FROM dish_templates WHERE template_id = ?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, template.getId());
            if (s.executeUpdate() == 1) {
                return;
            }
            throw new SQLException("ERROR WHILE DELETING TEMPLATE " + template.getName());
        }
    }
}
