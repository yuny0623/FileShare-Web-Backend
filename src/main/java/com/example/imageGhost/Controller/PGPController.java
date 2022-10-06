package com.example.imageGhost.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PGPController {

    public PGPController(){

    }

    /*
        PGP 방식으로 데이터 보내기
     */
    @PostMapping("/pgp")
    public void sendMessageViaPGPController(){

    }

    /*
        PGP 방식으로 데이터 받기
     */
    @GetMapping("/pgp/{public-key}")
    public void receiveMessageViaPGPController(@PathVariable("public-key") String publicKey){

    }
}
