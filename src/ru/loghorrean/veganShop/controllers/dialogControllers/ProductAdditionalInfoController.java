package ru.loghorrean.veganShop.controllers.dialogControllers;

import ru.loghorrean.veganShop.controllers.DialogController;
import ru.loghorrean.veganShop.controllers.IInit;
import ru.loghorrean.veganShop.models.database.entities.DatabaseEntity;
import ru.loghorrean.veganShop.models.database.entities.Product;

public class ProductAdditionalInfoController extends DialogController implements IInit {
    private Product product;

    @Override
    public void initialize() {

    }

    @Override
    public boolean checkFields() {
        return false;
    }

    @Override
    public void initData(DatabaseEntity object) {
        product = (Product) object;
    }
}
