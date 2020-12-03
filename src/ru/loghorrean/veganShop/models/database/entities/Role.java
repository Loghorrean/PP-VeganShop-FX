package ru.loghorrean.veganShop.models.database.entities;

import java.util.HashSet;
import java.util.Set;

public class Role extends DatabaseEntity {
    private String title;
    private final Set<User> usersWithRole;

    public Role(int id, String title) {
        super(id);
        this.title = title;
        usersWithRole = new HashSet<>();
    }

    public Role(String title) {
        this(-1, title);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void addUser(User user) {
        usersWithRole.add(user);
    }

    public void removeUser(User user) {
        usersWithRole.remove(user);
    }

    public Set<User> getUsersWithRole() {
        return usersWithRole;
    }

    @Override
    public String toString() {
        return "RoleEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
