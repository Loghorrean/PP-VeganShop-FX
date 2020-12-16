package ru.loghorrean.veganShop.controllers.admin;

import javafx.event.ActionEvent;
import ru.loghorrean.veganShop.controllers.AdminControllerWithGrid;

public class AdminUsersController extends AdminControllerWithGrid {
    @Override
    public void openAddDialog(ActionEvent event) {

    }

    @Override
    public void initialize() {
        mainBorderPane.setRight(getUserMenu());
        mainBorderPane.setBottom(getBackButton());
    }
}
