package ru.loghorrean.veganShop.models;

import ru.loghorrean.veganShop.models.database.entities.CategoriesForTemplate;
import ru.loghorrean.veganShop.models.database.entities.DishTemplate;
import ru.loghorrean.veganShop.models.database.entities.ProductCategory;
import ru.loghorrean.veganShop.models.database.managers.CategoriesForTemplateManager;

import java.sql.SQLException;
import java.util.List;

public class CategoriesForTemplatesData {
    private static CategoriesForTemplatesData instance;
    private CategoriesForTemplateManager manager;
    private List<CategoriesForTemplate> links;

    private CategoriesForTemplatesData() {
        try {
            manager = new CategoriesForTemplateManager();
            setLinks();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static CategoriesForTemplatesData getInstance() {
        if (instance == null) {
            instance = new CategoriesForTemplatesData();
        }
        return instance;
    }

    public void setLinks() {
        try {
            links = manager.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addLink(CategoriesForTemplate link) throws SQLException {
        links.add(link);
        manager.insert(link);
    }

    public void removeLink(CategoriesForTemplate link) throws SQLException {
        links.remove(link);
        manager.delete(link);
    }

    public boolean checkIfLinkExists(ProductCategory category, DishTemplate template) {
        for (CategoriesForTemplate link: links) {
            if (link.getCategory() == category && link.getTemplate() == template) {
                return true;
            }
        }
        return false;
    }

    public CategoriesForTemplate findLink(ProductCategory category, DishTemplate template) {
        for (CategoriesForTemplate link: links) {
            if (link.getCategory() == category && link.getTemplate() == template) {
                return link;
            }
        }
        return null;
    }
}
