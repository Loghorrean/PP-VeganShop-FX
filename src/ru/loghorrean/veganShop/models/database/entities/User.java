package ru.loghorrean.veganShop.models.database.entities;

import java.util.HashSet;
import java.util.Set;

public class User extends DatabaseEntity {
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;
    private String salt;
    private City city;
    private String street;
    private int house;
    private int flat;
    private final Set<Review> reviewsOfUser;
    private final Set<Order> ordersOfUser;
    private final Set<CustomDish> customDishesByUser;

    private User(int id) {
        super(id);
        reviewsOfUser = new HashSet<>();
        ordersOfUser = new HashSet<>();
        customDishesByUser = new HashSet<>();
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public Role getRole() {
        return role;
    }

    public String getSalt() {
        return salt;
    }

    public City getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public int getHouse() {
        return house;
    }

    public int getFlat() {
        return flat;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setHouse(int house) {
        this.house = house;
    }

    public void setFlat(int flat) {
        this.flat = flat;
    }

    public void addReview(Review review) {
        reviewsOfUser.add(review);
    }

    public void removeReview(Review review) {
        reviewsOfUser.remove(review);
    }

    public Set<Review> getReviewsOfUser() {
        return reviewsOfUser;
    }

    public void addOrder(Order order) {
        ordersOfUser.add(order);
    }

    public void removeOrder(Order order) {
        ordersOfUser.remove(order);
    }

    public Set<Order> getOrdersOfUser() {
        return ordersOfUser;
    }

    public void addCustomDish(CustomDish customDish) {
        customDishesByUser.add(customDish);
    }

    public void removeCustomDish(CustomDish customDish) {
        customDishesByUser.remove(customDish);
    }

    public Set<CustomDish> getCustomDishesByUser() {
        return customDishesByUser;
    }

    public static class UserBuilder {
        private final User user;

        public UserBuilder() {
            user = new User(-1);
        }

        public UserBuilder withId(int id) {
            user.setId(id);
            return this;
        }

        public UserBuilder withUsername(String username) {
            user.setUsername(username);
            return this;
        }

        public UserBuilder withEmail(String email) {
            user.setEmail(email);
            return this;
        }

        public UserBuilder withPassword(String password) {
            user.password = password;
            return this;
        }

        public UserBuilder withFirstName(String firstName) {
            user.setFirstName(firstName);
            return this;
        }

        public UserBuilder withLastName(String lastName) {
            user.setLastName(lastName);
            return this;
        }

        public UserBuilder withPhone(String phone) {
            user.setPhone(phone);
            return this;
        }

        public UserBuilder withRole(Role role) {
            user.setRole(role);
            return this;
        }

        public UserBuilder withSalt(String salt) {
            user.salt = salt;
            return this;
        }

        public UserBuilder withCity(City city) {
            user.city = city;
            return this;
        }

        public UserBuilder withStreet(String street) {
            user.setStreet(street);
            return this;
        }

        public UserBuilder withHouse(int house) {
            user.setHouse(house);
            return this;
        }

        public UserBuilder withFlat(int flat) {
            user.setFlat(flat);
            return this;
        }

        public User build() {
            return this.user;
        }
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", role=" + role +
                ", salt='" + salt + '\'' +
                ", city=" + city +
                ", street='" + street + '\'' +
                ", house=" + house +
                ", flat=" + flat +
                '}';
    }
}
