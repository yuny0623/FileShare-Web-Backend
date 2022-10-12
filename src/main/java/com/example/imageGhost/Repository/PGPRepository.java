package com.example.imageGhost.Repository;

import com.example.imageGhost.Domain.PGPMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PGPRepository extends JpaRepository<PGPMessage, Long> {

    PGPMessage save(PGPMessage pgpMessage);

    List<PGPMessage> findAllByReceiverPublicKey(String receiverPublicKey);
    List<PGPMessage> findAllBySenderPublicKey(String senderPublicKey);
    Optional<PGPMessage> findById(Long id);
}
