package ru.loghorrean.veganShop.models.database.entities;

import java.time.LocalDate;

public class Review extends DatabaseEntity {
    private int id;
    private String content;
    private LocalDate review_date;
    private boolean isApproved;
    private int stars;
    private GeneralDish dish;
    private User userCreated;

    public Review(int id, String content, LocalDate review_date, boolean isApproved, int stars, GeneralDish dish, User userCreated) {
        this.id = id;
        this.content = content;
        this.review_date = review_date;
        this.isApproved = isApproved;
        this.stars = stars;
        this.dish = dish;
        this.userCreated = userCreated;
    }

    public Review(String content, LocalDate review_date, boolean isApproved, int stars, GeneralDish dish, User userCreated) {
        this(-1, content, review_date, isApproved, stars, dish, userCreated);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getReview_date() {
        return review_date;
    }

    public void setReview_date(LocalDate review_date) {
        this.review_date = review_date;
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
