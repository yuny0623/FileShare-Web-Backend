package com.example.imageGhost.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class UtilController {

    @GetMapping("/health-check")
    public String healthCheck(){
        return "health-check";
    }
}
