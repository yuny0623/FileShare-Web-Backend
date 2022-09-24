package com.example.imageGhost.Domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Seller {
    @Id
    @GeneratedValue
    private Long id;

    private String publicKey;
    private String intro;
    private int point;

    private String BTCAddress;
    private String ETHAddress;
}
