package ru.loghorrean.veganShop.models;

import ru.loghorrean.veganShop.models.database.entities.DishTemplate;
import ru.loghorrean.veganShop.models.database.managers.TemplatesManager;

import java.sql.SQLException;
import java.util.List;

public class TemplatesData {
    private static TemplatesData instance;
    private List<DishTemplate> templates;
    private TemplatesManager manager;

    public static TemplatesData getInstance() throws SQLException {
        if (instance == null) {
            instance = new TemplatesData();
        }
        return instance;
    }

    private TemplatesData() throws SQLException {
        manager = new TemplatesManager();
        setTemplates();
    }

    public void setTemplates() throws SQLException {
        templates = manager.getAllTemplates();
    }

    public List<DishTemplate> getTemplates() {
        return templates;
    }

    public void addTemplateToModel(DishTemplate template) throws SQLException {
        manager.insertTemplate(template);
        templates.add(template);
    }

    public void updateTemplateInModel(DishTemplate template) throws SQLException {
        manager.updateTemplate(template);
    }

    public void deleteTemplateInModel(DishTemplate template) throws SQLException {
        manager.deleteTemplate(template.getId());
        templates.remove(template);
    }
}
