package com.example.imageGhost.Domain.Dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthAnswerDto {
    public String senderPublicKey; // 유저 public key
    public String answer;          // 유저가 보낸 정답지
}
