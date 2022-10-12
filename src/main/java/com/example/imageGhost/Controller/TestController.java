package com.example.imageGhost.Controller;


import com.example.imageGhost.Domain.Dto.EncFileDto;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @PostMapping("/test-post")
    public EncFileDto testPostController(@RequestBody EncFileDto encFileDto){
        System.out.println(encFileDto.getOwnerPublicKey());
        System.out.println(encFileDto.getCiphertext());
        return encFileDto;
    }

    @GetMapping("/test-get/{data}")
    public boolean testGetWithNoDataController(@PathVariable("data") String data){
        System.out.println("is checked.");
        System.out.println("Path Variable is:" + data);
        return true;
    }
}
