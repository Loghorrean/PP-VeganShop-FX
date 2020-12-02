package ru.loghorrean.veganShop.models;

import ru.loghorrean.veganShop.models.database.entities.DishTemplate;
import ru.loghorrean.veganShop.models.database.managers.DishTemplatesManager;

import java.sql.SQLException;
import java.util.List;

public class TemplatesData {
    private static TemplatesData instance;
    private List<DishTemplate> templates;
    private final DishTemplatesManager manager;

    public static TemplatesData getInstance() throws SQLException {
        if (instance == null) {
            instance = new TemplatesData();
        }
        return instance;
    }

    private TemplatesData() throws SQLException {
        manager = new DishTemplatesManager();
        setTemplates();
    }

    public void setTemplates() throws SQLException {
        templates = manager.getAll();
    }

    public List<DishTemplate> getTemplates() {
        return templates;
    }

    public void addTemplateToModel(DishTemplate template) throws SQLException {
        manager.insert(template);
        templates.add(template);
    }

    public void updateTemplateInModel(DishTemplate template) throws SQLException {
        manager.update(template);
    }

    public void deleteTemplateInModel(DishTemplate template) throws SQLException {
        manager.delete(template);
        templates.remove(template);
    }

    public boolean checkIfTemplateExists(String templateName) {
        for(DishTemplate template: templates) {
            if (template.getName().equals(templateName)) {
                return true;
            }
        }
        return false;
    }

    public DishTemplate getTemplateByName(String name) {
        for(DishTemplate template: templates) {
            if (template.getName().equals(name)) {
                return template;
            }
        }
        return null;
    }

    public DishTemplate getTemplateById(int id) {
        for(DishTemplate template: templates) {
            if (template.getId() == id) {
                return template;
            }
        }
        return null;
    }

    public static void unsetModel() {
        instance = null;
    }
}
