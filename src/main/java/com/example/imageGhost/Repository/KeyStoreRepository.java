package com.example.imageGhost.Repository;

import com.example.imageGhost.Domain.KeyStore;
import org.springframework.data.jpa.repository.JpaRepository;


public interface KeyStoreRepository extends JpaRepository<KeyStore, String> {
    KeyStore save(KeyStore keyStore);
    KeyStore findByPublicKey(String publicKey); // 이거 엔티티 이름 바꿔야 할듯...
 }

