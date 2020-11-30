package ru.loghorrean.veganShop.models.database.managers;

import ru.loghorrean.veganShop.models.database.entities.Review;

import java.sql.SQLException;
import java.util.List;

public class ReviewsManager extends BaseManager<Review> {
    public ReviewsManager() throws SQLException {
        super();
    }

    @Override
    public List<Review> getAll() throws SQLException {
        return null;
    }

    @Override
    public Review getOne(int id) throws SQLException {
        return null;
    }

    @Override
    public void insert(Review entity) throws SQLException {

    }

    @Override
    public void update(Review entity) throws SQLException {

    }

    @Override
    public void delete(Review entity) throws SQLException {

    }
}
