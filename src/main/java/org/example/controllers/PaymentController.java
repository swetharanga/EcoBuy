package org.example.controllers;

import org.example.dto.PaymentResponse;
import org.example.models.Product;
import org.example.services.PaymentService;
import org.example.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/checkout")

public class PaymentController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private  PaymentService paymentService;

    public PaymentController(PaymentService paymentService, ShoppingCartService shoppingCartService) {
        this.paymentService = paymentService;
        this.shoppingCartService = shoppingCartService;
    }


    @PostMapping("/api/cart/shopping-cart/place-order")
    public ResponseEntity<PaymentResponse> checkoutProduct(@RequestBody Product product){


        PaymentResponse paymentResponse = paymentService.checkoutItems(product);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(paymentResponse);
    }
    @GetMapping("/success")
    public String success() {
        // Handle successful payment here
        return "success";  // Success page
    }

    @GetMapping("/cancel")
    public String cancel() {
        // Handle cancelled payment here
        return "cancel";   // Cancel page
    }



    // Checkout is redirected from Shopping Cart and the product_id from shopping_cart db is checked out
    // Dynamically calculate the total price for each item and pass it to the checkout page


}