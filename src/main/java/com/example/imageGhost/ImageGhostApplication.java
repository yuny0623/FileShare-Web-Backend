package com.example.imageGhost;


import com.example.imageGhost.Utils.ServerMetaInfoGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;

@SpringBootApplication
public class ImageGhostApplication {
	public static void main(String[] args) {
		SpringApplication.run(ImageGhostApplication.class, args);
		// 서버 메모리 상에 비대칭키 키페어 올려두기
		HashMap<String, String> keyPair = ServerMetaInfoGenerator.generateKeyPair();

		String publicKey = keyPair.get("publicKey");
		String privateKey = keyPair.get("privateKey");

		// 설정 파일에 등록
		Config.setServerPublicKey(publicKey);
		Config.setServerPrivateKey(privateKey);
	}
}
