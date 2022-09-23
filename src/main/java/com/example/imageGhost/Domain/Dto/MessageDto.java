package com.example.imageGhost.Domain.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDto {
    private String senderPublicKey;
    private String receiverPublicKey;
    private String content;
}
