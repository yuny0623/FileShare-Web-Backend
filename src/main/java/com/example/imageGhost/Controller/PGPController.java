package com.example.imageGhost.Controller;


import com.example.imageGhost.Domain.PGPMessage;
import com.example.imageGhost.Repository.PGPRepository;
import com.example.imageGhost.Service.PGPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class PGPController {
    private PGPRepository pgpRepository;
    private PGPService pgpService;

    @Autowired
    public PGPController(PGPRepository pgpRepository){
        this.pgpRepository = pgpRepository;
    }

    /*
        PGP 방식으로 데이터 보내기
     */
    @PostMapping("/pgp")
    public PGPMessage sendMessageViaPGPController(@RequestBody PGPMessage pgpMessage){
        return pgpRepository.save(pgpMessage);
    }

    /*
        PGP 방식으로 데이터 받기
     */
    @GetMapping("/pgp/{public-key}")
    public List<PGPMessage> receiveMessageViaPGPController(@PathVariable("public-key") String publicKey){
        List<PGPMessage> pgpMessageList;
        try {
            pgpMessageList = pgpRepository.findAllByReceiverPublicKey(publicKey);
        }catch(NoSuchElementException e){
            e.printStackTrace();
            return new ArrayList<>();
        }
        return pgpMessageList;
    }
}
