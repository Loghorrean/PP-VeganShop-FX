package ru.loghorrean.veganShop.models;

import ru.loghorrean.veganShop.models.database.entities.CustomDish;
import ru.loghorrean.veganShop.models.database.entities.CustomDishInOrder;
import ru.loghorrean.veganShop.models.database.entities.GeneralDishInOrder;
import ru.loghorrean.veganShop.models.database.entities.Order;
import ru.loghorrean.veganShop.models.database.managers.BaseManager;
import ru.loghorrean.veganShop.models.database.managers.CustomDishesInOrderManager;

import java.sql.SQLException;
import java.util.List;

public class CustomInOrderData {
    private static CustomInOrderData instance;
    private BaseManager<CustomDishInOrder> manager;
    private List<CustomDishInOrder> links;

    private CustomInOrderData() {
        try {
            manager = new CustomDishesInOrderManager();
            setLinks();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static CustomInOrderData getInstance() {
        if (instance == null) {
            instance = new CustomInOrderData();
        }
        return instance;
    }

    public void setLinks() throws SQLException {
        links = manager.getAll();
    }

    public void addLinkToModel(CustomDishInOrder link) throws SQLException {
        manager.insert(link);
        links.add(link);
    }

    public void updateLinkInModel(CustomDishInOrder link) throws SQLException {
        manager.update(link);
    }

    public void deleteLinkInModel(CustomDishInOrder link) throws SQLException {
        manager.delete(link);
        links.remove(link);
    }

    public CustomDishInOrder getLink(CustomDish dish, Order order) {
        for (CustomDishInOrder link: links) {
            if (link.getDish() == dish && link.getOrder() == order) {
                return link;
            }
        }
        return null;
    }

    public boolean checkIFLinkExists(CustomDish dish, Order order) {
        return getLink(dish, order) != null;
    }
}
