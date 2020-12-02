package ru.loghorrean.veganShop.controllers;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.Pane;
import ru.loghorrean.veganShop.util.DialogCreator;

abstract public class AdminControllerWithGrid extends AdminPanelController {
    @Override
    public void openInfoDialog(Pane paneForDialog) {
        Dialog<ButtonType> dialog =
                new DialogCreator.DialogBuilder("HowToUseGridDialog")
                .createDialog("Как работать", paneForDialog)
                .addButtons(ButtonType.OK)
                .build();
        dialog.showAndWait();
    }
}
