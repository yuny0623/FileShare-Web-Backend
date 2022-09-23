package com.example.imageGhost.Repository;


import com.example.imageGhost.Domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    Message save(Message message);
    List<Message> findAllByReceiverPublicKey(String publicKey);
}
