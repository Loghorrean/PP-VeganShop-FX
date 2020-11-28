package ru.loghorrean.veganShop.models.database.managers;

import ru.loghorrean.veganShop.models.database.MySQLDatabase;
import ru.loghorrean.veganShop.models.database.entities.DishTemplate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TemplatesManager {
    private MySQLDatabase database;

    public TemplatesManager() {
        database = MySQLDatabase.getInstance();
    }

    public List<DishTemplate> getAllTemplates() throws SQLException {
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

    public DishTemplate getTemplateById(int id) throws SQLException {
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

    public void insertTemplate(DishTemplate template) throws SQLException {
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

            throw new SQLException("ERROR WHILE ADDING TEMPLATE");
        }
    }

    public void updateTemplate(DishTemplate template) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "UPDATE dish_templates SET template_name = ?, template_description = ? WHERE template_id = ?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setString(1, template.getName());
            s.setString(2, template.getDescription());
            s.setInt(3, template.getId());
            s.executeUpdate();
        }
    }

    public void deleteTemplate(int id) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "DELETE FROM dish_templates WHERE template_id = ?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, id);
            s.executeUpdate();
        }
    }
}
