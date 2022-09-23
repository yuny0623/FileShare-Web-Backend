package com.example.imageGhost.Controller;

import com.example.imageGhost.Domain.Dto.MessageDto;
import com.example.imageGhost.Domain.Message;
import com.example.imageGhost.Domain.User;
import com.example.imageGhost.Domain.Dto.UserDto;
import com.example.imageGhost.Repository.MessageRepository;
import com.example.imageGhost.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class UserController {
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    @Autowired
    public UserController(UserRepository userRepository, MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    @PostMapping("/join")
    public String registerPublicKey(@RequestBody UserDto userDto) {
        User user = new User();
        user.setPublicKey(userDto.getPublicKey());
        user.setIntro(userDto.getIntro());
        user.setPoint(0);
        userRepository.save(user);
        return user.getPublicKey();
    }

    @GetMapping("/users")
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @PostMapping("/box")
    public Message sendToSomeone(@RequestBody MessageDto messageDto) {
        Message message = new Message();
        message.setReceiverPublicKey(messageDto.getReceiverPublicKey());
        message.setSenderPublicKey(messageDto.getSenderPublicKey());
        message.setContent(messageDto.getContent());
        return messageRepository.save(message);
    }

    @GetMapping("/box/{public-key}")
    public List<Message> getMyMessage(@PathVariable("public-key") String publicKey) {
        return messageRepository.findAllByReceiverPublicKey(publicKey);
    }
}
