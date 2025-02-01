package org.example.services;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.example.dto.PaymentResponse;
import org.example.models.Product;
import org.example.models.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService {

    @Value("${STRIPE_SECRET_KEY}") // Fixed annotation
    private String stripeSecretKey;

    @Autowired
    private ShoppingCartService shoppingCartService;

    public PaymentResponse checkoutItems(Product product) {
        // Set Stripe API key
        Stripe.apiKey = stripeSecretKey;

        List<ShoppingCart> cartItems = shoppingCartService.getCartItems(); // Fetch cart items
        List<SessionCreateParams.LineItem> lineItems = new ArrayList<>();
        BigDecimal totalAmount = BigDecimal.ZERO; // Initialize total amount

        // Iterate through cart items to calculate total and build line items
        for (ShoppingCart item : cartItems) {
            BigDecimal price = item.getProduct().getPrice(); // Item price
            Integer quantity = item.getQuantity(); // Quantity
            String name = item.getProduct().getName(); // Product name

            // Add item's total price to totalAmount
            totalAmount = totalAmount.add(price.multiply(BigDecimal.valueOf(quantity)));

            // Create line item for Stripe checkout
            SessionCreateParams.LineItem lineItem = SessionCreateParams.LineItem.builder()
                    .setQuantity(Long.valueOf(quantity))
                    .setPriceData(
                            SessionCreateParams.LineItem.PriceData.builder()
                                    .setCurrency("usd") // Set your desired currency
                                    .setUnitAmount(price.multiply(BigDecimal.valueOf(100)).longValue()) // Convert to cents
                                    .setProductData(
                                            SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                    .setName(name)
                                                    .build()
                                    )
                                    .build()
                    )
                    .build();

            lineItems.add(lineItem); // Add to line items list
        }

        // Create Stripe session
        SessionCreateParams params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:8080/success") // Redirect URL for success
                .setCancelUrl("http://localhost:8080/cancel") // Redirect URL for cancellation
                .addAllLineItem(lineItems) // Add all line items
                .build();

        try {
            Session session = Session.create(params); // Create session in Stripe

            // Build and return PaymentResponse
            return PaymentResponse.builder()
                    .status("success")
                    .message("Payment session created successfully.")
                    .sessionId(session.getId())
                    .sessionUrl(session.getUrl())
                    .build();
        } catch (StripeException ex) {
            // Handle exception and return error response
            return PaymentResponse.builder()
                    .status("error")
                    .message("Error creating payment session: " + ex.getMessage())
                    .build();
        }
    }
}
