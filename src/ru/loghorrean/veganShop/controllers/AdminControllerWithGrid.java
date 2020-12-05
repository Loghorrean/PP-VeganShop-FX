package ru.loghorrean.veganShop.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import ru.loghorrean.veganShop.util.DialogCreator;

abstract public class AdminControllerWithGrid extends AdminPanelController {
    @FXML
    protected GridPane mainGridPane;

    @Override
    public void openInfoDialog(Pane paneForDialog) {
        Dialog<ButtonType> dialog =
                new DialogCreator.DialogBuilder("adminDialogs/HowToUseGridDialog")
                .createDialog("Как работать", paneForDialog)
                .addButtons(ButtonType.OK)
                .build();
        dialog.showAndWait();
    }

    public void addRow() {

    }

    public void deleteRow(int rowNumber) {
        mainGridPane.getChildren().removeIf(node -> GridPane.getRowIndex(node) == rowNumber);
    }

    public void clearRow(int rowNumber) {

    }

    public void moveRows() {

    }
}
