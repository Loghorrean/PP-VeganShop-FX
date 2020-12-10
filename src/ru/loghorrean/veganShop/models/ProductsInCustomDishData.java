package ru.loghorrean.veganShop.models;

import ru.loghorrean.veganShop.models.database.entities.CustomDish;
import ru.loghorrean.veganShop.models.database.entities.Product;
import ru.loghorrean.veganShop.models.database.entities.ProductInCustomDish;
import ru.loghorrean.veganShop.models.database.managers.BaseManager;
import ru.loghorrean.veganShop.models.database.managers.ProductsInCustomDishManager;

import java.sql.SQLException;
import java.util.List;

public class ProductsInCustomDishData {
    private static ProductsInCustomDishData instance;
    private BaseManager<ProductInCustomDish> manager;
    private List<ProductInCustomDish> links;

    private ProductsInCustomDishData() {
        try {
            manager = new ProductsInCustomDishManager();
            setLinks();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ProductsInCustomDishData getInstance() {
        if (instance == null) {
            instance = new ProductsInCustomDishData();
        }
        return instance;
    }

    public void setLinks() throws SQLException {
        links = manager.getAll();
    }

    public void addLinkToModel(ProductInCustomDish link) throws SQLException {
        manager.insert(link);
        links.add(link);
    }

    public void updateLinkInModel(ProductInCustomDish link) throws SQLException {
        manager.update(link);
    }

    public void deleteLinkInModel(ProductInCustomDish link) throws SQLException {
        manager.delete(link);
        links.remove(link);
    }

    public ProductInCustomDish getLink(Product product, CustomDish dish) {
        for (ProductInCustomDish link: links) {
            if (link.getProduct() == product && link.getDish() == dish) {
                return link;
            }
        }
        return null;
    }

    public boolean checkIfLinkExists(Product product, CustomDish dish) {
        return getLink(product, dish) != null;
    }
}
