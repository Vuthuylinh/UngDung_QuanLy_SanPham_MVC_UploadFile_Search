package service;

import model.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    List<Product> findByName(String name);

    Product findById(int id);

    void save(Product product);

    void update(int id, Product product);

    void delete(int id);
}
