package com.example.imageGhost.Controller;

import com.example.imageGhost.Domain.Dto.EncFileDto;
import com.example.imageGhost.Domain.EncFile;
import com.example.imageGhost.Repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FileController {

    private final FileRepository fileRepository;

    @Autowired
    public FileController(FileRepository fileRepository){
        this.fileRepository = fileRepository;
    }

    @GetMapping("/file/{publilc-key}")
    public List<EncFile> getAllMyFile(@PathVariable("public-key") String publicKey){
        return fileRepository.findAllByOwnerPublicKey(publicKey);
    }

    @PostMapping("file")
    public EncFile saveFile(@RequestBody EncFileDto encFileDto){
        EncFile encFile = new EncFile();
        encFile.setCiphertext(encFile.getCiphertext());
        encFile.setOwnerPublicKey(encFile.getOwnerPublicKey());
        return fileRepository.save(encFile);
    }
}
