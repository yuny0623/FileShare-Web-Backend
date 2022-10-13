package com.example.imageGhost.Controller;

import com.example.imageGhost.Config;
import com.example.imageGhost.Domain.AuthAnswer;
import com.example.imageGhost.Domain.User;
import com.example.imageGhost.Repository.AuthAnswerRepository;
import com.example.imageGhost.Repository.UserRepository;
import com.example.imageGhost.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
public class AuthController {

    private AuthAnswerRepository authAnswerRepository;
    private AuthService authService;
    private UserRepository userRepository;

    @Autowired
    public AuthController(AuthAnswerRepository authAnswerRepository, AuthService authService, UserRepository userRepository){
        this.authAnswerRepository = authAnswerRepository;
        this.authService = authService;
        this.userRepository = userRepository;
    }

    /*
        인증절차 1 - 문제 발행 후 받아오기
     */
    @PostMapping("/auth-problem/{public-key}")
    public String getAuthProblem(@PathVariable("public-key") String publicKey){
        String plainText = UUID.randomUUID().toString(); // 랜덤 생성 plain text
        AuthAnswer authAnswer = new AuthAnswer();
        authAnswer.setSenderPublicKey(publicKey);
        authAnswer.setRandomStringAnswer(plainText); // public key로 encrypt 필요.
        authAnswer.setCipherText(authService.encryptPlainTextWithPublicKey(plainText, publicKey));
        authAnswer.setAuthenticated(false);
        authAnswerRepository.save(authAnswer);
        return authAnswer.getCipherText(); // 암호화된 답안지를 client로 전송
    }

    /*
        사용자에게 제시된 인증 문제 다시 받아오기.
     */
    @GetMapping("/auth-problem/{public-key}")
    public String getMyProblem(@PathVariable("public-key") String publicKey){
        if(!authService.isAuthenticatedUser(publicKey)){
            return new String("");
        }
        AuthAnswer authAnswer = authAnswerRepository.findBySenderPublicKey(publicKey);
        return authAnswer.getCipherText(); // 문제 재전송
    }


    /*
        인증절차 2
        client 에서 SERVER_PUBLIC_KEY 로 잠궈서 답안을 보냄.
        SERVER 에서는 SERVER_PRIVATE_KEY로 답안을 열어서 답안지를 확인.
     */
    @PostMapping("/auth-answer/{public-key}/{answer}")
    public boolean solveAuthProblem(@PathVariable("public-key") String publicKey, @PathVariable("answer") String cipherTextedAnswer){
        try {
            User findUser = userRepository.findByPublicKey(publicKey);
        }catch(NoSuchElementException e){
            e.printStackTrace();
            return false;
        }
        // SERVER_PUBLIC_KEY로 암호화해서 보낸 답안지를 SERVER_PRIVATE_KEY로 복호화
        String plainTextAnswer = authService.decryptCipherTextWithPrivateKey(cipherTextedAnswer, Config.SERVER_PRIVATE_KEY);

        AuthAnswer authAnswer = authAnswerRepository.findBySenderPublicKey(publicKey);
        if(authAnswer.getRandomStringAnswer().equals(plainTextAnswer)){
            authAnswer.setAuthenticated(true); // 인증 처리
            authAnswerRepository.save(authAnswer);
            return true;
        }else{
            return false; // 정답지가 틀려서 인증 실패
        }
    }
}
