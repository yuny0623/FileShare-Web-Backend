package com.example.imageGhost.Domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    private Long price;
    private String sellerPublicKey;
    private String intro;
}
