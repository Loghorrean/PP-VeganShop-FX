package ru.loghorrean.veganShop.models.database.entities;

import ru.loghorrean.veganShop.exceptions.DatabaseException;

public class Role extends DatabaseEntity {
    private String title;

    public Role(int id, String title) throws DatabaseException {
        super(id);
        this.title = title;
    }

    public Role(String title) throws DatabaseException {
        this(-1, title);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "RoleEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
