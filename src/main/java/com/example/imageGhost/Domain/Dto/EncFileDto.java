package com.example.imageGhost.Domain.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class EncFileDto {
    private String ownerPublicKey;
    private String ciphertext;
}
