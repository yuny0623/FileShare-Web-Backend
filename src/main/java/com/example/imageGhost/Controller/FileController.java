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
        존재하는 모든 EncFile 조회
     */
    @GetMapping("/files")
    public List<EncFile> getAllEncFile(){
        return fileRepository.findAll();
    }

//    /*
//        EncFile 삭제 api. 인증 정보가 없으면 삭제 불가능.
//     */
//    @DeleteMapping("/file/{public-key}")
//    public boolean deleteFile(@RequestBody AuthAnswerDto authAnswerDto){
//        AuthAnswer authAnswer = null;
//        try {
//            authAnswer = authAnswerRepository.findBySenderPublicKey(authAnswerDto.getSenderPublicKey()); // 인증 정보가 존재해야 delete 가능.
//        }catch(NoSuchElementException e){
//            e.printStackTrace();
//            return false;
//        }
//        if(authAnswer.getRandomString().equals(authAnswerDto.getAnswer())){
//            // 1. EncFile 삭제
//            fileRepository.deleteByOwnerPublicKey(authAnswerDto.getSenderPublicKey());
//            // 2. AuthAnsewr 삭제
//            authAnswerRepository.deleteBySenderPublicKey(authAnswerDto.getSenderPublicKey());
//            return true;
//        }else{
//            return false;
//        }
//    }

    /*
        내 EncFile 삭제 api - 인증된 사용자만 사용 가능
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

}
