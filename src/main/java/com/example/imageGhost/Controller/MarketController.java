package com.example.imageGhost.Controller;

import com.example.imageGhost.Domain.Product;
import com.example.imageGhost.Domain.Seller;
import com.example.imageGhost.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MarketController {

    private final UserRepository userRepository;

    @Autowired
    public MarketController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping("/product")
    public List<Product> getProduct(){

    }
}
