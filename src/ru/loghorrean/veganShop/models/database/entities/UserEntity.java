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

    private void setId(int id) {
        this.id = id;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    private void setLastName(String lastName) {
        this.lastName = lastName;
    }

    private void setPhone(String phone) {
        this.phone = phone;
    }

    private void setRole(RoleEntity role) {
        this.role = role;
    }

    private void setSalt(String salt) {
        this.salt = salt;
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
                ", role=" + role.getTitle() +
                ", dateOfReg=" + dateOfReg +
                '}';
    }
}
