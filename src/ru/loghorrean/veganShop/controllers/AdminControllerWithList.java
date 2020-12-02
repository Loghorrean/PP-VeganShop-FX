package ru.loghorrean.veganShop.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import ru.loghorrean.veganShop.util.DialogCreator;

abstract public class AdminControllerWithList<T> extends AdminPanelController {
    @FXML
    protected ListView<T> mainListView;

    @Override
    public void openInfoDialog(Pane paneForDialog) {
        Dialog<ButtonType> dialog =
                new DialogCreator.DialogBuilder("adminDialogs/HowToUseListDialog")
                        .createDialog("Как работать", paneForDialog)
                        .addButtons(ButtonType.OK)
                        .build();
        dialog.showAndWait();
    }
}
