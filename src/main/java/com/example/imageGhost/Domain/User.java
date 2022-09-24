package com.example.imageGhost.Domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Table(name="hi4")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String publicKey;
    private String intro;
    private int point;
}
