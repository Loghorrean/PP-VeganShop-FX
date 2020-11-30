package ru.loghorrean.veganShop.models.database.managers;

import ru.loghorrean.veganShop.models.database.entities.CategoriesForTemplate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CategoriesForTemplateManager extends BaseManager<CategoriesForTemplate> {
    public CategoriesForTemplateManager() throws SQLException {
        super();
    }

    @Override
    public List<CategoriesForTemplate> getAll() throws SQLException {
        return null;
    }

    @Override
    public CategoriesForTemplate getOne(int id) throws SQLException {
        return null;
    }

    @Override
    public void insert(CategoriesForTemplate entity) throws SQLException {

    }

    @Override
    public void update(CategoriesForTemplate entity) throws SQLException {

    }

    @Override
    public void delete(CategoriesForTemplate entity) throws SQLException {

    }
}
