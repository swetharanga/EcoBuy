package org.example.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.example.models.Product;
import org.example.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private  ProductRepository productRepository;


    @PersistenceContext
    private EntityManager entityManager;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    @Query("SELECT p FROM Product p WHERE p.id = :id")
    public Optional<Product> findProductById(@Param("productId") Integer id)
    {
        entityManager.clear();
        entityManager.flush();  // Ensure that any changes to the database are committed

        return productRepository.findById(id);

      //  return productRepository.findById(id);
    }

    @Override
    public void deleteProduct(Integer id) {

    }


    @Override
    public Product saveProduct(Integer id) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }


}
