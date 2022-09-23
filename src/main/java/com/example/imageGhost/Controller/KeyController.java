package com.example.imageGhost.Controller;

import com.example.imageGhost.Domain.User;
import com.example.imageGhost.Domain.UserDto;
import com.example.imageGhost.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class KeyController {
    @Autowired
    private final UserRepository userRepository;

    public KeyController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public String registerPublicKey(@RequestBody UserDto userDto){
        User user = new User();
        user.setPublicKey(userDto.getPublicKey());
        user.setIntro(userDto.getIntro());
        user.setPoint(0);
        userRepository.save(user);
        return user.getIntro();
    }
}
