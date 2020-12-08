package ru.loghorrean.veganShop.models;

import ru.loghorrean.veganShop.models.database.entities.GeneralDish;
import ru.loghorrean.veganShop.models.database.entities.Product;
import ru.loghorrean.veganShop.models.database.entities.ProductInGeneralDish;
import ru.loghorrean.veganShop.models.database.managers.BaseManager;
import ru.loghorrean.veganShop.models.database.managers.ProductsInGeneralDishManager;

import java.sql.SQLException;
import java.util.List;

public class ProductsInGeneralDishesData {
    private static ProductsInGeneralDishesData instance;
    private BaseManager<ProductInGeneralDish> manager;
    private List<ProductInGeneralDish> links;

    private ProductsInGeneralDishesData() {
        try {
            manager = new ProductsInGeneralDishManager();
            setLinks();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setLinks() throws SQLException {
        links = manager.getAll();
    }

    public void addLinkToModel(ProductInGeneralDish link) throws SQLException {
        manager.insert(link);
        links.add(link);
    }

    public void updateLinkInModel(ProductInGeneralDish link) throws SQLException {
        manager.update(link);
    }

    public void removeLinkFromModel(ProductInGeneralDish link) throws SQLException {
        manager.delete(link);
        links.remove(link);
    }

    public ProductInGeneralDish getLink(Product product, GeneralDish dish) {
        for (ProductInGeneralDish link: links) {
            if (link.getProduct() == product && link.getDish() == dish) {
                return link;
            }
        }
        return null;
    }

    public boolean checkIfLinkExists(Product product, GeneralDish dish) {
        return getLink(product, dish) != null;
     }
}
