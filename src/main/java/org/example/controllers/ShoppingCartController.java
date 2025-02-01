package org.example.controllers;

import jakarta.annotation.PostConstruct;
import org.example.models.Product;
import org.example.models.ShoppingCart;
import org.example.repositories.ShoppingCartRepository;
import org.example.services.ProductService;
import org.example.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping(value ="/api/cart")

public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ProductService productService;

    @PostConstruct
    public void checkConnectionOnStartup() throws SQLException {
        checkConnection();
    }

    public void checkConnection() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            if (connection.isValid(2)) {
                System.out.println("Database connection is valid.");

                // Check if we are connected to the correct database by querying the 'products' table
                String query = "SELECT COUNT(*) FROM products";
                try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
                    if (rs.next()) {
                        int count = rs.getInt(1);
                        System.out.println("Connected to the correct database. Number of products in 'products' table: " + count);
                    }
                } catch (SQLException e) {
                    System.out.println("Failed to query the 'products' table: " + e.getMessage());
                }
            } else {
                System.out.println("Failed to connect to the database.");
            }
        }
    }


    @GetMapping("/print-products")
    public String printProducts() {
        shoppingCartService.getCartItems();
        return "ShoppingCart"; // Redirect after printing products
    }

    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable Integer id, Integer quantity,Model model) {
        Optional<Product> product = productService.findProductById(id);
        if (product.isPresent()) {
            // Add the product to the cart (replace with your logic)
            shoppingCartService.addProductToCart(id,quantity);
            model.addAttribute("product", product.get());
        } else {
            model.addAttribute("error", "Product not found!");
        }
        return "redirect:/print-products"; // Redirect to display updated cart
    }

    @PostMapping("/add")
    public String  addToCart(@RequestParam ("productId")Integer productId, @RequestParam int quantity, Model model) {
        System.out.println("Request to add product to cart with productId: " + productId + " and quantity: " + quantity);

        shoppingCartService.addProductToCart(productId, quantity);
        System.out.println("Request received to add item: " + productService.findProductById(productId));
        Optional<Product> product = productService.findProductById(productId);
        System.out.println(product);
        if (product.isEmpty()) {
            throw new ProductNotFoundException("Product with ID " + productId + " not found.");
        }

        System.out.println("Received productId: " + productId);
        System.out.println("Received quantity: " + quantity);

        List<ShoppingCart> cartItems = shoppingCartService.getCartItems();
        model.addAttribute("cartItems", cartItems);
        System.out.println(cartItems);

       return "redirect:/api/cart/ShoppingCart";

    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class ProductNotFoundException extends RuntimeException {
        public ProductNotFoundException(String message) {
            super(message);
        }
    }

@GetMapping("/ShoppingCart")
public String showShoppingCart(Model model) {
    // Retrieve all cart items
    List<ShoppingCart> cartItems = shoppingCartService.getCartItems();

    // Add them to the model
    model.addAttribute("cartItems", cartItems);

    return "ShoppingCart";
}


    @PostMapping("/delete/{productId}")
    public ResponseEntity<String> deleteFromCart(@PathVariable Integer productId,Integer quantity) {
        Optional<ShoppingCart> shoppingService = shoppingCartRepository.findById(productId);
               if (shoppingService.isEmpty()) {
            throw new ProductNotFoundException("Product with ID " + productId + " not found.");
        }

        shoppingCartService.deleteProductFromCart(productId,quantity);
        return ResponseEntity.ok("Item deleted");

    }

    @GetMapping("/Checkout")
    public String showCheckoutPage(Model model){
        model.addAttribute("message", "Review your order before finalizing the checkout.");

        return "Checkout";
    }

    @PostMapping("/Checkout")
    public String CheckoutPage(Model model)
    {

        // Add success message
        model.addAttribute("message", "Checkout successful!");

        List<ShoppingCart> cartItems = shoppingCartService.getCartItems(); // Fetch cart items from service
        BigDecimal checkoutPrice = BigDecimal.ONE;

        // Calculate total price
        for (ShoppingCart item : cartItems) {
            BigDecimal price = item.getProduct().getPrice();
            Integer quantity = item.getQuantity();
            checkoutPrice = checkoutPrice.add(price.multiply(BigDecimal.valueOf(quantity)));

        }
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalPrice", checkoutPrice); // Pass total price to the model
        System.out.println("the total price is " + checkoutPrice);
        return "checkout";

    }
}
