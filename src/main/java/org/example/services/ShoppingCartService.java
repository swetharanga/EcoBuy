package org.example.services;


import org.example.models.ShoppingCart;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShoppingCartService {


    void addProductToCart(Integer productId, Integer quantity);

    List<ShoppingCart> getCartItems();

    void printAllProducts();

    void deleteProductFromCart(Integer productId, Integer quantity);
   // Optional<Product> findShoppingCartProductById(Integer Id);

}
