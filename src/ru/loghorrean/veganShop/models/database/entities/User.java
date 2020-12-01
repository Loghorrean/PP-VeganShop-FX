package ru.loghorrean.veganShop.models.database.entities;

import ru.loghorrean.veganShop.exceptions.DatabaseException;
import ru.loghorrean.veganShop.exceptions.UserException;
import ru.loghorrean.veganShop.util.validators.Validator;

public class User extends DatabaseEntity {
    private String username = "";
    private String email = "";
    private String password = "";
    private String firstName = "";
    private String lastName = "";
    private String phone = "";
    private Role role = null;
    private String salt = "";
    private City city = null;
    private String street = "";
    private int house = -1;
    private int flat = -1;

    private User(int id) throws DatabaseException {
        super(id);
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

    public static class UserBuilder {
        private User user;

        public UserBuilder() throws DatabaseException {
            user = new User(-1);
        }

        public UserBuilder withId(int id) throws DatabaseException {
            user.setId(id);
            return this;
        }

        public UserBuilder withUsername(String username) throws UserException {
            if (username.length() < 2 || username.length() > 20) {
                throw new UserException("Никнейм должен содержать от 2 до 20 символов");
            }
            user.setUsername(username);
            return this;
        }

        public UserBuilder withEmail(String email) throws UserException {
            if (Validator.validateMail(email)) {
                throw new UserException("Email is not valid");
            }
            user.email = email;
            return this;
        }

        public UserBuilder withPassword(String password) {
            user.password = password;
            return this;
        }

        public UserBuilder withFirstName(String firstName) throws UserException {
            if (!firstName.isEmpty()) {
                if (firstName.length() < 2 || firstName.length() > 30) {
                    throw new UserException("Имя должно содержать от 2 до 30 символов");
                }
            }
            user.firstName = firstName;
            return this;
        }

        public UserBuilder withLastName(String lastName) throws UserException {
            user.lastName = lastName;
            return this;
        }

        public UserBuilder withPhone(String phone) throws UserException {
            user.phone = phone;
            return this;
        }

        public UserBuilder withRole(Role role) throws UserException {
            user.role = role;
            return this;
        }

        public UserBuilder withSalt(String salt) throws UserException {
            user.salt = salt;
            return this;
        }

        public UserBuilder withCity(City city) {
            user.city = city;
            return this;
        }

        public UserBuilder withStreet(String street) throws UserException {
            user.street = street;
            return this;
        }

        public UserBuilder withHouse(int house) throws UserException {
            user.house = house;
            return this;
        }

        public UserBuilder withFlat(int flat) throws UserException {
            user.flat = flat;
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
