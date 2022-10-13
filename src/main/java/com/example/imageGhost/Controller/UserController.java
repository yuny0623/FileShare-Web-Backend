package com.example.imageGhost.Controller;

import com.example.imageGhost.Domain.Dto.UserDto;
import com.example.imageGhost.Domain.KeyStore;
import com.example.imageGhost.Domain.User;
import com.example.imageGhost.Repository.MessageRepository;
import com.example.imageGhost.Repository.KeyStoreRepository;
import com.example.imageGhost.Repository.UserRepository;
import com.example.imageGhost.Utils.ServerMetaInfoGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;
    private final KeyStoreRepository keyStoreRepository;

    @Autowired
    public UserController(UserRepository userRepository, MessageRepository messageRepository, KeyStoreRepository keyStoreRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
        this.keyStoreRepository = keyStoreRepository;
    }

    /*
        User 회원가입
     */
    @PostMapping("/public-key")
    public String registerPublicKey(@RequestBody UserDto userDto) {
        // 중복 가입 방지
        if(ServerMetaInfoGenerator.publicKeyList.contains(userDto.getPublicKey())){
            return new String("이미 존재하는 회원입니다.");
        }
        User user = new User();
        user.setPublicKey(userDto.getPublicKey());
        user.setIntro(userDto.getIntro());
        user.setPoint(0);
        userRepository.save(user);
        ServerMetaInfoGenerator.publicKeyList.add(userDto.getPublicKey()); // 서버에 등록
        KeyStore keyStore = new KeyStore();
        keyStore.setPublicKey(userDto.getPublicKey());

        keyStoreRepository.save(keyStore);
        return user.getPublicKey();
    }

    /*
        모든 유저 조회
     */
    @GetMapping("/user")
    public List<User> getAllUser() {
        return userRepository.findAll();
    }


}
