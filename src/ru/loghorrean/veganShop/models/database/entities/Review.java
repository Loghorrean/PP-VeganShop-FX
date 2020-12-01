package ru.loghorrean.veganShop.models.database.entities;

import ru.loghorrean.veganShop.exceptions.DatabaseException;

public class Review extends DatabaseEntity {
    private String content;
    private boolean isApproved;
    private int stars;
    private GeneralDish dish;
    private User userCreated;

    public Review(int id, String content, boolean isApproved, int stars, GeneralDish dish, User userCreated) throws DatabaseException {
        super(id);
        this.content = content;
        this.isApproved = isApproved;
        this.stars = stars;
        this.dish = dish;
        this.userCreated = userCreated;
    }

    public Review(String content, boolean isApproved, int stars, GeneralDish dish, User userCreated) throws DatabaseException {
        this(-1, content, isApproved, stars, dish, userCreated);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public GeneralDish getDish() {
        return dish;
    }

    public void setDish(GeneralDish dish) {
        this.dish = dish;
    }

    public User getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(User userCreated) {
        this.userCreated = userCreated;
    }
}
