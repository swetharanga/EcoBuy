package org.example.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.example.models.Product;
import org.example.models.ShoppingCart;
import org.example.repositories.ProductRepository;
import org.example.repositories.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    public void printAllProducts() {
        List<Product> products = productRepository.findAll();
        System.out.println("Total products in database: " + products.size());
        products.forEach(product -> System.out.println(product.getId() + ": " + product.getName()));
    }


    public List<ShoppingCart> getCartItems() {
        // Retrieve all shopping cart items from the database

            List<ShoppingCart> cartItems = shoppingCartRepository.findAll();

            // Log the product details
            for (ShoppingCart cartItem : cartItems) {
                System.out.println("Product name: " + cartItem.getProduct().getName());
                System.out.println("Product price: " + cartItem.getProduct().getPrice());
            }

            return cartItems;
        }



    @Override
    public void addProductToCart(Integer productId, Integer quantity) {
        // For simplicity, assume you add this directly to a session or cart object
        System.out.println("addProductToCart method called with productId: " + productId);
        if (productId == null || productId <= 0) {
            throw new RuntimeException("Invalid product ID");
        }
        Optional<Product> productOpt = productService.findProductById(productId);
        System.out.println(productOpt);
        if (productOpt.isPresent()) {
            System.out.println("yes");
            Product product = productOpt.get();
            ShoppingCart cartItem = new ShoppingCart(productId, quantity, product.getName(), product.getImageUrl());

            shoppingCartRepository.save(cartItem);
        }else {
            System.out.println("Product not found with ID: " + productId + " and quantity: " + quantity);
            throw new RuntimeException("Product not found!");
        }
    }

    @Transactional
    @Query("SELECT p FROM ShoppingCart p WHERE p.productId = :id")
    public Optional<ShoppingCart> findProductById(@Param("productId") Integer id)
    {
        entityManager.clear();
        entityManager.flush();  // Ensure that any changes to the database are committed

        return shoppingCartRepository.findById(id);

        //  return productRepository.findById(id);
    }

    @Transactional
    public void deleteProductFromCart(Integer productId, Integer quantity){
        Optional<ShoppingCart> productOpt = shoppingCartRepository.findById(productId);
        if (productOpt.isPresent()) {
            System.out.println("yes");
            shoppingCartRepository.deleteById(productId);
        }
        else {
            System.out.println("Product not found in cart.");
        }
    }
}
