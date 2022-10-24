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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
public class FileController {

    private final FileRepository fileRepository;
    private final AuthAnswerRepository authAnswerRepository;
    private final AuthService authService;
    private final UserRepository userRepository;

    // Logger 설정
    private final Logger logger = LoggerFactory.getLogger("FileController Log");

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
        List<EncFile> encFiles;
        try {
            encFiles = fileRepository.findAllByOwnerPublicKey(publicKey);
        }catch(NoSuchElementException e){
            e.printStackTrace();
            return new ArrayList<>(); // return empty list
        }
        return encFiles;
    }

    /*
        파일 등록하기
     */
    @PostMapping("/file")
    public EncFile saveFile(@RequestBody EncFileDto encFileDto){
        // logging
        logger.info(encFileDto.getOwnerPublicKey() + ": saved file.");

        EncFile encFile = new EncFile();
        encFile.setCiphertext(encFileDto.getCiphertext());
        encFile.setOwnerPublicKey(encFileDto.getOwnerPublicKey());
        return fileRepository.save(encFile);
    }

    /*
        존재하는 모든 EncFile 조회
     */
    @GetMapping("/files")
    public List<EncFile> getAllEncFile(){
        return fileRepository.findAll();
    }

    /*
        내 EncFile 삭제 api - 인증된 사용자만 사용 가능
     */
    @DeleteMapping("/file/{public-key}")
    public boolean deleteEncFile(@PathVariable("public-key") String publicKey){
        if(!authService.isAuthenticatedUser(publicKey)){
            return false;
        }

        // logging
        logger.info(publicKey + ": deleted file.");

        fileRepository.deleteByOwnerPublicKey(publicKey);
        return true;
    }
}
