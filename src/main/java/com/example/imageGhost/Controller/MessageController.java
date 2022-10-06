package com.example.imageGhost.Controller;

import com.example.imageGhost.Domain.Dto.MessageDto;
import com.example.imageGhost.Domain.Message;
import com.example.imageGhost.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {
    private final MessageRepository messageRepository;

    @Autowired
    public MessageController(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    @PostMapping("/message")
    public Message sendToSomeone(@RequestBody MessageDto messageDto) {
        Message message = new Message();
        message.setReceiverPublicKey(messageDto.getReceiverPublicKey());
        message.setSenderPublicKey(messageDto.getSenderPublicKey());
        message.setContent(messageDto.getContent());
        return messageRepository.save(message);
    }

    @GetMapping("/message/{public-key}")
    public List<Message> getMyMessage(@PathVariable("public-key") String publicKey) {
        /*
            AuthAnswer 를 활용한 본인인증 기능 필요함.
         */

        return messageRepository.findAllByReceiverPublicKey(publicKey);
    }
}
