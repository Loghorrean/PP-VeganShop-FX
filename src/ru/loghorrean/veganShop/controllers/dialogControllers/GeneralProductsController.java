package ru.loghorrean.veganShop.controllers.dialogControllers;

import ru.loghorrean.veganShop.controllers.DialogController;
import ru.loghorrean.veganShop.controllers.IFill;
import ru.loghorrean.veganShop.controllers.IInit;
import ru.loghorrean.veganShop.models.database.entities.DatabaseEntity;
import ru.loghorrean.veganShop.models.database.entities.ProductInGeneralDish;

public class GeneralProductsController extends DialogController implements IInit, IFill {
    private ProductInGeneralDish link;

    @Override
    public void fillDialog() {

    }

    @Override
    public void initData(DatabaseEntity object) {
        link = (ProductInGeneralDish) object;
    }

    @Override
    public void initialize() {

    }

    @Override
    public boolean checkFields() {
        return false;
    }
}
