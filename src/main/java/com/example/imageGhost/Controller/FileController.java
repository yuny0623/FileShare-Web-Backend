package com.example.imageGhost.Controller;

import com.example.imageGhost.Domain.AuthAnswer;
import com.example.imageGhost.Domain.Dto.AuthAnswerDto;
import com.example.imageGhost.Domain.Dto.EncFileDto;
import com.example.imageGhost.Domain.EncFile;
import com.example.imageGhost.Repository.AuthAnswerRepository;
import com.example.imageGhost.Repository.FileRepository;
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

    @Autowired
    public FileController(FileRepository fileRepository, AuthAnswerRepository authAnswerRepository, AuthService authService){
        this.fileRepository = fileRepository;
        this.authAnswerRepository = authAnswerRepository;
        this.authService = authService;
    }

    /*
        내 파일 조회
     */
    @GetMapping("/file/{publilc-key}")
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
            authAnswer = authAnswerRepository.findBySenderPublicKey(authAnswerDto.getSenderPublicKey());
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
        인증 정보 생성
        random String을 public key로 암호화 ->
        private key 로 암호화된 text를 복호화

        random String 과 전달받은 값을 비교함.
     */
    @PostMapping("/delete-auth/{public-key}")
    public String createAuthAnswer(@PathVariable("public-key") String publicKey){
        String plainText = UUID.randomUUID().toString(); // 랜덤 생성 plain text

        AuthAnswer authAnswer = new AuthAnswer();
        authAnswer.setSenderPublicKey(publicKey);
        authAnswer.setRandomString(plainText); // public key로 encrypt 필요.
        authAnswer.setCipherText(authService.encryptPlainText(plainText, publicKey));
        authAnswer.setAuthenticated(false);
        authAnswerRepository.save(authAnswer);
        return authAnswer.getCipherText(); // 암호화된 답안지를 client로 전송
    }
}
