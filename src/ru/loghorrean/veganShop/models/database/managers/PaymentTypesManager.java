package ru.loghorrean.veganShop.models.database.managers;

import ru.loghorrean.veganShop.models.database.MySQLDatabase;
import ru.loghorrean.veganShop.models.database.entities.PaymentType;

import java.sql.SQLException;
import java.util.List;

public class PaymentTypesManager extends BaseManager<PaymentType> {
    public PaymentTypesManager() {
        super();
    }

    @Override
    public List<PaymentType> getAll() throws SQLException {
        return null;
    }

    @Override
    public PaymentType getOne(int id) throws SQLException {
        return null;
    }

    @Override
    public void insert(PaymentType entity) throws SQLException {

    }

    @Override
    public void update(PaymentType entity) throws SQLException {

    }

    @Override
    public void delete(PaymentType entity) throws SQLException {

    }
}
