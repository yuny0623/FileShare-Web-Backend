package com.example.imageGhost.Controller;


import com.example.imageGhost.Domain.Dto.SellerDto;
import com.example.imageGhost.Domain.Seller;
import com.example.imageGhost.Repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SellerController {

    private final SellerRepository sellerRepository;

    @Autowired
    public SellerController(SellerRepository sellerRepository){
        this.sellerRepository = sellerRepository;
    }

    /*
        판매자로 등록
     */
    @PostMapping("/join-seller")
    public Seller registerSeller(@RequestBody SellerDto sellerDto){
        Seller seller = new Seller();
        seller.setPublicKey(seller.getPublicKey());
        seller.setIntro(seller.getIntro());
        seller.setBTCAddress(sellerDto.getBTCAddress());
        seller.setETHAddress(sellerDto.getETHAddress());
        seller.setPoint(0);
        return sellerRepository.save(seller);
    }

    /*
        모든 판매자 조회
     */
    @GetMapping("/sellers")
    public List<Seller> getAllSeller(){
        return sellerRepository.findAll();
    }
}
