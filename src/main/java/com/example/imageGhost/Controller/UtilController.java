package com.example.imageGhost.Controller;

import com.example.imageGhost.Utils.ServerMetaInfoGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UtilController {
    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/health-check")
    public String healthCheck(){
        return "health-check";
    }

    /*
        Server Public Key 받기
     */
    @GetMapping("/server-public-key")
    public String getServerPublicKey(){
        return ServerMetaInfoGenerator.SERVER_PUBLIC_KEY;
    }
}
