package com.example.imageGhost.Domain.Dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthAnswerDto {
    public String senderPublicKey;
    public String answer;
}
