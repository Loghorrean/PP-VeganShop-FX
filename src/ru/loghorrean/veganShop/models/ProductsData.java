package ru.loghorrean.veganShop.models;

import ru.loghorrean.veganShop.models.database.entities.Product;
import ru.loghorrean.veganShop.models.database.managers.ProductsManager;

import java.sql.SQLException;
import java.util.List;

public class ProductsData {
    private static ProductsData instance;
    private ProductsManager productsManager;
    private List<Product> products;

    private ProductsData() {
        try {
            productsManager = new ProductsManager();
            setProducts();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ProductsData getInstance() {
        if (instance == null) {
            instance = new ProductsData();
        }
        return instance;
    }

    public void setProducts() throws SQLException {
        products = productsManager.getAll();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProductToModel(Product product) throws SQLException {
        productsManager.insert(product);
        products.add(product);
    }

    public void updateProductInModel(Product product) throws SQLException {
        productsManager.update(product);
    }

    public void deleteProductInModel(Product product) throws SQLException {
        productsManager.delete(product);
        products.remove(product);
    }

    public Product getProductByName(String name) {
        for(Product product: products) {
            if (product.getName().equals(name)) {
                return product;
            }
        }
        return null;
    }

    public Product getProductById(int id) {
        for(Product product: products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
}
