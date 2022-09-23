package com.example.imageGhost.Repository;

import com.example.imageGhost.Domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product save(Product product);
    Product findById(Long id);
    List<Product> findAll();
}
