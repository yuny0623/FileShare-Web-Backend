package com.example.imageGhost.Domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {
    @Id
    private Long id;

    private Long price;
    private String sellerPublicKey;
    private String intro;
}
