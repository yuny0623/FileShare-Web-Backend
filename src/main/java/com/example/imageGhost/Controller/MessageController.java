package com.example.imageGhost.Controller;

import com.example.imageGhost.Domain.Dto.MessageDto;
import com.example.imageGhost.Domain.Message;
import com.example.imageGhost.Repository.MessageRepository;
import com.example.imageGhost.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class MessageController {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    @Autowired
    public MessageController(MessageRepository messageRepository, UserRepository userRepository){
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    /*
        타인 메시지함에 메시지 보내기
     */
    @PostMapping("/message")
    public Message sendToSomeone(@RequestBody MessageDto messageDto) {
        Message message = new Message();
        message.setReceiverPublicKey(messageDto.getReceiverPublicKey());
        message.setSenderPublicKey(messageDto.getSenderPublicKey());
        message.setContent(messageDto.getContent());
        return messageRepository.save(message);
    }

    /*
        내 메시지함 조회하기
     */
    @GetMapping("/message/{public-key}")
    public List<Message> getMyMessage(@PathVariable("public-key") String publicKey) {
        List<Message> messageList;
        try {
            messageList = messageRepository.findAllByReceiverPublicKey(publicKey);
        }catch(NoSuchElementException e){
            e.printStackTrace();
            return new ArrayList<>(); // return empty list
        }
        return messageList;
    }

    /*
        내 메시지함 비우기
        delete api 는 인증 후 사용 가능.
     */
    @DeleteMapping("/message/{public-key}")
    public void deleteAllMyMessage(@PathVariable("public-key") String publicKey){
        /*
            유저 인증 필요.
         */
        messageRepository.deleteAllByReceiverPublicKey(publicKey);
    }

    /*
        메시지 한건 조회
     */
    @GetMapping("/message/{public-key}/{id}")
    public Message getMySingleMessage(@PathVariable("public-key") String publicKey, @PathVariable("id") Long id){
        Message findMessage;
        try {
            findMessage = messageRepository.findByReceiverPublicKeyAndId(publicKey, id);
        }catch(NoSuchElementException e){
            e.printStackTrace();
            return new Message();
        }
        return findMessage;
    }
}
