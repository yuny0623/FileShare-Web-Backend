package com.example.imageGhost.Repository;


import com.example.imageGhost.Domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {
    Message save(Message message);
    List<Message> findAllByReceiverPublicKey(String publicKey);
    void deleteAllByReceiverPublicKey(String publicKey);
    Optional<Message> findById(Long id);

    Message findByReceiverPublicKeyAndId(String receiverPublicKey, Long id);
}
