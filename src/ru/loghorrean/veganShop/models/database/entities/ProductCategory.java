package ru.loghorrean.veganShop.models.database.entities;

import ru.loghorrean.veganShop.exceptions.CategoryException;
import ru.loghorrean.veganShop.exceptions.CityException;
import ru.loghorrean.veganShop.exceptions.DatabaseException;

import java.util.Set;

public class ProductCategory extends DatabaseEntity {
    private String name;
    private String description;
    private Set<DishTemplate> templates;

    public ProductCategory(int id, String name, String description) throws DatabaseException {
        super(id);
        setName(name);
        setDescription(description);
    }

    public ProductCategory(String name, String description) throws DatabaseException {
        this(-1, name, description);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws CategoryException {
        if (description.length() > 30) {
            throw new CategoryException("Длина название категории не должна превышать 30 символов");
        }
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws CategoryException {
        if (description.length() > 100) {
            throw new CategoryException("Описание категории не должно превышать 100 символов");
        }
        this.description = description;
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
