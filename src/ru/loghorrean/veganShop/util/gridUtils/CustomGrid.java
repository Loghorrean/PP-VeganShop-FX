package ru.loghorrean.veganShop.util.gridUtils;

import javafx.scene.layout.GridPane;

import java.util.List;

public class CustomGrid<T> extends GridPane {
    private List<CustomGrid<T>> customGridRows;

    public CustomGrid() {
        super();
    }
}
