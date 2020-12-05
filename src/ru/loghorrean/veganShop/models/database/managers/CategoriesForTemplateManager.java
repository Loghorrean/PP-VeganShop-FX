package ru.loghorrean.veganShop.models.database.managers;

import ru.loghorrean.veganShop.models.CategoriesData;
import ru.loghorrean.veganShop.models.TemplatesData;
import ru.loghorrean.veganShop.models.database.entities.CategoriesForTemplate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriesForTemplateManager extends BaseManager<CategoriesForTemplate> {
    public CategoriesForTemplateManager() throws SQLException {
        super();
    }

    @Override
    public List<CategoriesForTemplate> getAll() throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT * FROM categories_for_template";
            Statement s = c.createStatement();
            ResultSet set = s.executeQuery(sql);
            List<CategoriesForTemplate> entities = new ArrayList<>();
            while (set.next()) {
                entities.add(new CategoriesForTemplate(
                        set.getInt("record_id"),
                        CategoriesData.getInstance().getCategoryById(set.getInt("category_id")),
                        TemplatesData.getInstance().getTemplateById(set.getInt("template_id"))
                ));
            }
            return entities;
        }
    }

    @Override
    public CategoriesForTemplate getOne(int id) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "SELECT * FROM categories_for_templates WHERE record_id = ?";
            PreparedStatement s = c.prepareStatement(sql);
            s.setInt(1, id);
            ResultSet set = s.executeQuery();
            if (set.next()) {
                return new CategoriesForTemplate(
                        set.getInt("record_id"),
                        CategoriesData.getInstance().getCategoryById(set.getInt("category_id")),
                        TemplatesData.getInstance().getTemplateById(set.getInt("template_id"))
                );
            }
            return null;
        }
    }

    @Override
    public void insert(CategoriesForTemplate link) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "INSERT INTO categories_for_template (category_id, template_id) VALUES (?, ?)";
            PreparedStatement s = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            s.setInt(1, link.getCategory().getId());
            s.setInt(2, link.getTemplate().getId());
            s.executeUpdate();
            ResultSet set = s.getGeneratedKeys();
            if (set.next()) {
                link.setId(set.getInt(1));
                return;
            }
            throw new SQLException("ERROR WHILE INSERT CATEGORIES AND TEMPLATES LINK");
        }
    }

    @Override
    public void update(CategoriesForTemplate link) throws SQLException {
        System.out.println("HOW DID YOU MANAGE TO CALL THIS METHOD?!");
    }

    @Override
    public void delete(CategoriesForTemplate link) throws SQLException {
        try (Connection c = database.getConnection()) {
            String sql = "DELETE FROM categories_for_template WHERE record_id = ?";
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
