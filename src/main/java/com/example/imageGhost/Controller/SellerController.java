package com.example.imageGhost.Controller;


import com.example.imageGhost.Domain.Seller;
import com.example.imageGhost.Domain.SellerDto;
import com.example.imageGhost.Repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class SellerController {

    private final SellerRepository sellerRepository;

    @Autowired
    public SellerController(SellerRepository sellerRepository){
        this.sellerRepository = sellerRepository;
    }

    @PostMapping("/register")
    public SellerDto registerSeller(@RequestBody SellerDto sellerDto){
        Seller seller = new Seller();
        seller.setPublicKey(seller.getPublicKey());
        seller.setIntro(seller.getIntro());
        seller.setBTCAddress(sellerDto.getBTCAddress());
        seller.setETHAddress(sellerDto.getETHAddress());
        seller.setPoint(0);
        sellerRepository.save(seller);
        return sellerDto;
    }

    @GetMapping("/sellers")
    public List<Seller> getAllSeller(){
        return sellerRepository.findAll();
    }
}