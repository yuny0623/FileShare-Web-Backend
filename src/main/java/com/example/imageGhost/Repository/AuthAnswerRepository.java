package com.example.imageGhost.Repository;

import com.example.imageGhost.Domain.AuthAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthAnswerRepository extends JpaRepository<AuthAnswer, String> {
    AuthAnswer save(AuthAnswer authAnswer);
    AuthAnswer findBySenderPublicKey(String publicKey);
}
