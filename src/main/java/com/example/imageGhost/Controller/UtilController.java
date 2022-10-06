package com.example.imageGhost.Controller;

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
}
