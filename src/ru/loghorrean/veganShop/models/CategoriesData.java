package ru.loghorrean.veganShop.models;

import ru.loghorrean.veganShop.models.database.entities.ProductCategory;
import ru.loghorrean.veganShop.models.database.managers.ProductCategoriesManager;

import java.sql.SQLException;
import java.util.List;

public class CategoriesData {
    private static CategoriesData instance;
    private List<ProductCategory> categories;
    private ProductCategoriesManager manager;

    public static CategoriesData getInstance() {
        if (instance == null) {
            instance = new CategoriesData();
        }
        return instance;
    }

    private CategoriesData() {
        try {
            manager = new ProductCategoriesManager();
            setCategories();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void setCategories() throws SQLException {
        categories = manager.getAll();
    }

    public List<ProductCategory> getCategories() {
        return this.categories;
    }

    public void addCategoryToModel(ProductCategory category) throws SQLException {
        manager.insert(category);
        categories.add(category);
    }

    public void updateCategoryInModel(ProductCategory category) throws SQLException {
        manager.update(category);
    }

    public void deleteCategoryInModel(ProductCategory category) throws SQLException {
        manager.delete(category);
        categories.remove(category);
    }

    public boolean checkIfCatExists(String catName) {
        for(ProductCategory category: categories) {
            if (category.getName().equals(catName)) {
                return true;
            }
        }
        return false;
    }

    public ProductCategory getCategoryByName(String name) {
        for (ProductCategory category: categories) {
            if (category.getName().equals(name)) {
                return category;
            }
        }
        return null;
    }

    public ProductCategory getCategoryById(int id) {
        for (ProductCategory category: categories) {
            if (category.getId() == id) {
                return category;
            }
        }
        return null;
    }

    public static void unsetModel() {
        instance = null;
    }
}
