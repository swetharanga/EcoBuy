package org.example.repositories;

import org.example.models.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {

    //Optional<ShoppingCart> findByProduct(Product product);
    //@Query("SELECT sc.products FROM ShoppingCart sc WHERE sc.id = :cartId")
    Optional<ShoppingCart> findById(Integer productId);


}
