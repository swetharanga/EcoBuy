package org.example.services;

import org.example.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<Product> findProductById(Integer Id);
    void deleteProduct(Integer id);
    Product saveProduct(Integer id);
    List<Product> getAllProducts();

}
