package ru.loghorrean.veganShop.models.database.entities;

import java.time.LocalDate;

public class UserEntity {
    private int id = -1;
    private String username = "";
    private String email = "";
    private String password = "";
    private String firstName = "";
    private String lastName = "";
    private String phone = "";
    private RoleEntity role = null;
    private LocalDate dateOfReg;
    private String salt = "";
    private CityEntity city = null;
    private String street = "";
    private int house = -1;
    private int flat = -1;

    public int getId() {
        return id;
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

    public RoleEntity getRole() {
        return role;
    }

    public String getSalt() {
        return salt;
    }

    public CityEntity getCity() {
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

    private void setId(int id) {
        this.id = id;
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

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setCity(CityEntity city) {
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
        private UserEntity user;

        public UserBuilder() {
            user = new UserEntity();
        }

        public UserBuilder withId(int id) {
            user.id = id;
            return this;
        }

        public UserBuilder withUsername(String username) {
            user.username = username;
            return this;
        }

        public UserBuilder withEmail(String email) {
            user.email = email;
            return this;
        }

        public UserBuilder withPassword(String password) {
            user.password = password;
            return this;
        }

        public UserBuilder withFirstName(String firstName) {
            user.firstName = firstName;
            return this;
        }

        public UserBuilder withLastName(String lastName) {
            user.lastName = lastName;
            return this;
        }

        public UserBuilder withPhone(String phone) {
            user.phone = phone;
            return this;
        }

        public UserBuilder withRole(RoleEntity role) {
            user.role = role;
            return this;
        }

        public UserBuilder withSalt(String salt) {
            user.salt = salt;
            return this;
        }

        public UserBuilder withCity(CityEntity city) {
            user.city = city;
            return this;
        }

        public UserBuilder withStreet(String street) {
            user.street = street;
            return this;
        }

        public UserBuilder withHouse(int house) {
            user.house = house;
            return this;
        }

        public UserBuilder withFlat(int flat) {
            user.flat = flat;
            return this;
        }

        public UserEntity build() {
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
                ", dateOfReg=" + dateOfReg +
                ", salt='" + salt + '\'' +
                ", city=" + city +
                ", street='" + street + '\'' +
                ", house=" + house +
                ", flat=" + flat +
                '}';
    }
}
