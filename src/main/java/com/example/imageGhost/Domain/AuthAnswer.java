package com.example.imageGhost.Domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthAnswer {
    @Id
    @GeneratedValue
    private Long id;

    private String randomString;     // original plain text
    private String cipherText;

    private String senderPublicKey;
    private boolean isAuthenticated; // 인증 여부 - default = false
}
