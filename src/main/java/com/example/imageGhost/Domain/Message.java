package com.example.imageGhost.Domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="hi")
public class Message {
    @Id
    @GeneratedValue
    private Long id;

    private String senderPublicKey;
    private String receiverPublicKey;
    private String content;
}
