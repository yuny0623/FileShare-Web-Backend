package com.example.imageGhost.Controller;


import com.example.imageGhost.Domain.PGPMessage;
import com.example.imageGhost.Utils.ServerMetaInfoGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class PGPController {

    /*
        PGP 방식으로 데이터 보내기
     */
    @PostMapping("/pgp")
    public PGPMessage sendMessageViaPGPController(@RequestBody PGPMessage pgpMessage){
        return ServerMetaInfoGenerator.PGP_MESSAGE_BOX.put(pgpMessage.getSenderPublicKey(), pgpMessage);
    }

    /*
        PGP 방식으로 데이터 받기
     */
    @GetMapping("/pgp/{public-key}")
    public PGPMessage receiveMessageViaPGPController(@PathVariable("public-key") String publicKey){
        PGPMessage pgpMessage;
        try {
            pgpMessage= ServerMetaInfoGenerator.PGP_MESSAGE_BOX.get(publicKey);
        }catch(NoSuchElementException e){
            e.printStackTrace();
            return new PGPMessage();
        }
        return pgpMessage;
    }
}
