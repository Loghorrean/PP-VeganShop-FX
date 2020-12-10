package ru.loghorrean.veganShop.models;

import ru.loghorrean.veganShop.models.database.entities.GeneralDish;
import ru.loghorrean.veganShop.models.database.entities.GeneralDishInOrder;
import ru.loghorrean.veganShop.models.database.entities.Order;
import ru.loghorrean.veganShop.models.database.managers.BaseManager;
import ru.loghorrean.veganShop.models.database.managers.GeneralDishesInOrderManager;

import java.sql.SQLException;
import java.util.List;

public class GeneralInOrderData {
    private static GeneralInOrderData instance;
    private BaseManager<GeneralDishInOrder> manager;
    private List<GeneralDishInOrder> links;

    private GeneralInOrderData() {
        try {
            manager = new GeneralDishesInOrderManager();
            setLinks();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static GeneralInOrderData getInstance() {
        if (instance == null) {
            instance = new GeneralInOrderData();
        }
        return instance;
    }

    private void setLinks() throws SQLException {
        links = manager.getAll();
    }

    public void addLinkToModel(GeneralDishInOrder link) throws SQLException {
        manager.insert(link);
        links.add(link);
    }

    public void updateLinkInModel(GeneralDishInOrder link) throws SQLException {
        manager.update(link);
    }

    public void removeLinkInMode(GeneralDishInOrder link) throws SQLException {
        manager.delete(link);
        links.remove(link);
    }

    public GeneralDishInOrder getLink(GeneralDish dish, Order order) {
        for (GeneralDishInOrder link: links) {
            if (link.getDish() == dish && link.getOrder() == order) {
                return link;
            }
        }
        return null;
    }

    public boolean checkIfLinkExists(GeneralDish dish, Order order) {
        return getLink(dish, order) != null;
    }
}
