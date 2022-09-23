package com.example.imageGhost.Repository;

import com.example.imageGhost.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String>{

    User save(String publicKey);
    User findByPublicKey(String publicKey);
}
