package com.example.imageGhost;


import com.example.imageGhost.Utils.ServerMetaInfoGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ImageGhostApplication {
	public static void main(String[] args) {
		SpringApplication.run(ImageGhostApplication.class, args);

		// 서버 메모리 상에 비대칭키 키페어 올려두기
		ServerMetaInfoGenerator.generateKeyPair();
	}
}
