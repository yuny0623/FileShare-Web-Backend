package com.example.imageGhost.Repository;

import com.example.imageGhost.Domain.EncFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<EncFile, Long> {

    List<EncFile> findAllByOwnerPublicKey(String publicKey);
    EncFile save(EncFile encfile);
}
