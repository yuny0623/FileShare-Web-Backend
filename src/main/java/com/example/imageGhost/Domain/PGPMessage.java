package com.example.imageGhost.Domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PGPMessage {
    @Id
    @GeneratedValue
    private Long id;
    private String content;

    private String senderPublicKey;   // 송신자 public key
    private String receiverPublicKey; // 수신자 public key
}
