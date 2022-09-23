package com.example.imageGhost.Domain.Dto;


import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SellerDto {
    private String publicKey;
    private String intro;

    private String BTCAddress;
    private String ETHAddress;
}
