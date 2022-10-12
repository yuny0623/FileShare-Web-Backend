package com.example.imageGhost.Controller;

import com.example.imageGhost.Domain.AuthAnswer;
import com.example.imageGhost.Domain.Dto.AuthAnswerDto;
import com.example.imageGhost.Domain.Dto.EncFileDto;
import com.example.imageGhost.Domain.EncFile;
import com.example.imageGhost.Domain.User;
import com.example.imageGhost.Repository.AuthAnswerRepository;
import com.example.imageGhost.Repository.FileRepository;
import com.example.imageGhost.Repository.UserRepository;
import com.example.imageGhost.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
public class FileController {

    private final FileRepository fileRepository;
    private final AuthAnswerRepository authAnswerRepository;
    private final AuthService authService;
    private final UserRepository userRepository;

    @Autowired
    public FileController(FileRepository fileRepository, AuthAnswerRepository authAnswerRepository, AuthService authService, UserRepository userRepository){
        this.fileRepository = fileRepository;
        this.authAnswerRepository = authAnswerRepository;
        this.authService = authService;
        this.userRepository = userRepository;
    }

    /*
        내 파일 조회
     */
    @GetMapping("/file/{public-key}")
    public List<EncFile> getAllMyFile(@PathVariable("public-key") String publicKey){
        return fileRepository.findAllByOwnerPublicKey(publicKey);
    }

    /*
        파일 저장하기
     */
    @PostMapping("/file")
    public EncFile saveFile(@RequestBody EncFileDto encFileDto){
        EncFile encFile = new EncFile();
        encFile.setCiphertext(encFile.getCiphertext());
        encFile.setOwnerPublicKey(encFile.getOwnerPublicKey());
        return fileRepository.save(encFile);
    }

    /*
        모든 EncFile 조회
     */
    @GetMapping("/files")
    public List<EncFile> getAllEncFile(){
        return fileRepository.findAll();
    }

    /*
        EncFile 삭제 api. 인증 정보가 없으면 삭제 불가능.
     */
    @DeleteMapping("/file/{public-key}")
    public boolean deleteFile(@RequestBody AuthAnswerDto authAnswerDto){
        AuthAnswer authAnswer = null;
        try {
            authAnswer = authAnswerRepository.findBySenderPublicKey(authAnswerDto.getSenderPublicKey()); // 인증 정보가 존재해야 delete 가능.
        }catch(NoSuchElementException e){
            e.printStackTrace();
            return false;
        }
        if(authAnswer.getRandomString().equals(authAnswerDto.getAnswer())){
            // 1. EncFile 삭제
            fileRepository.deleteByOwnerPublicKey(authAnswerDto.getSenderPublicKey());
            // 2. AuthAnsewr 삭제
            authAnswerRepository.deleteBySenderPublicKey(authAnswerDto.getSenderPublicKey());
            return true;
        }else{
            return false;
        }
    }

    /*
        EncFile 삭제 api - 인증된 사용자만 사용 가능
     */
    @PostMapping("/delete-auth/{public-key}")
    public boolean deleteEncFile(@PathVariable("public-key") String publicKey){
        if(authService.isAuthenticatedUser(publicKey)){
            fileRepository.deleteByOwnerPublicKey(publicKey);
            return true;
        }else{
            return false;
        }
    }

    /*
        인증절차 1 - 문제 발행 후 받아오기
     */
    @PostMapping("/auth-problem/{public-key}")
    public String getAuthProblem(@PathVariable("public-key") String publicKey){
        String plainText = UUID.randomUUID().toString(); // 랜덤 생성 plain text
        AuthAnswer authAnswer = new AuthAnswer();
        authAnswer.setSenderPublicKey(publicKey);
        authAnswer.setRandomString(plainText); // public key로 encrypt 필요.
        authAnswer.setCipherText(authService.encryptPlainText(plainText, publicKey));
        authAnswer.setAuthenticated(false);
        authAnswerRepository.save(authAnswer);
        return authAnswer.getCipherText(); // 암호화된 답안지를 client로 전송
    }

    /*
        사용자에게 제시된 인증 문제 다시 받아오기.
     */
    @GetMapping("/auth-problem/{public-key}")
    public String getMyProblem(@PathVariable("public-key") String publicKey){
        if(authService.isAuthenticatedUser(publicKey)){
            return new String(""); // empty string 리턴 -> 더 좋은 방법 있을듯?
        }
        AuthAnswer authAnswer = authAnswerRepository.findBySenderPublicKey(publicKey);
        return authAnswer.getCipherText(); // 문제 재전송
    }


    /*
        인증절차 2
        client 에서 SERVER_PUBLIC_KEY 로 잠궈서 답안을 보냄.
     */
    @PostMapping("/auth-answer/{public-key}/{answer}")
    public boolean solveAuthProblem(@PathVariable("public-key") String publicKey, @PathVariable("answer") String cipherTextedAnswer){
        try {
            User findUser = userRepository.findByPublicKey(publicKey);
        }catch(NoSuchElementException e){
            e.printStackTrace();
            return false;
        }
        String plainTextAnswer = authService.decryptCipherText(cipherTextedAnswer, publicKey);

        AuthAnswer authAnswer = authAnswerRepository.findBySenderPublicKey(publicKey);
        if(authAnswer.getRandomString().equals(plainTextAnswer)){
            authAnswer.setAuthenticated(true); // 인증 처리
            authAnswerRepository.save(authAnswer);
            return true;
        }else{
            return false; // 정답지가 틀려서 인증 실패
        }
    }

    /*
        Cipher 받기 -> Cipher 풀어서 제출 -> 등록됨.
        이후부터 api 사용할때는 인증된 유저인지 확인할 수 있음.
        서버 다운되면 메모리 날릴때 인증된 유저 목록도 날라감. <- 오히려 r디비에 유지하는 것보다 이편이 나을듯.
     */
}
