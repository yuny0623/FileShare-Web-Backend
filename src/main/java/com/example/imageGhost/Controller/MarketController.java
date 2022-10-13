package com.example.imageGhost.Controller;

import com.example.imageGhost.Domain.Product;
import com.example.imageGhost.Domain.Seller;
import com.example.imageGhost.Repository.ProductRepository;
import com.example.imageGhost.Repository.UserRepository;
import com.example.imageGhost.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MarketController {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Autowired
    public MarketController(UserRepository userRepository, ProductRepository productRepository){
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    /*
        상품 목록 조회
     */
    @GetMapping("/product")
    public List<Product> getProduct(){
        return productRepository.findAll();
    }
}
