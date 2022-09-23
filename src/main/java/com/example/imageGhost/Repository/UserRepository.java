package com.example.imageGhost.Repository;

import com.example.imageGhost.Domain.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String>{

    User save(String publicKey);
    User findByPublicKey(String publicKey);

    List<User> findAll();
}
