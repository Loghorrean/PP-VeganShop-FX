package ru.loghorrean.veganShop.models.database.entities;

public class RoleEntity {
    private int id;
    private String title;

    public RoleEntity(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public RoleEntity(String title) {
        this(-1, title);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
