package com.example.imageGhost.Repository;

import com.example.imageGhost.Domain.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerRepository extends JpaRepository<Seller, String> {
    @Override
    Seller save(Seller seller);
    List<Seller> findAll();
}
