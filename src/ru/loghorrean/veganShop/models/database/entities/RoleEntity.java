package ru.loghorrean.veganShop.models.database.entities;

public class RoleEntity {
    private int id;
    private String name;

    public RoleEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public RoleEntity(String name) {
        this(-1, name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "RoleEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
