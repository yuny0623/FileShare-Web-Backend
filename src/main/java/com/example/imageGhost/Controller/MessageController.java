package com.example.imageGhost.Controller;

import com.example.imageGhost.Domain.Dto.MessageDto;
import com.example.imageGhost.Domain.Message;
import com.example.imageGhost.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
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
        return messageRepository.findAllByReceiverPublicKey(publicKey);
    }
}
