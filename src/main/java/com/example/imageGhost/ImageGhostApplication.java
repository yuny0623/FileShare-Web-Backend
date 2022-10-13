package com.example.imageGhost;


import com.example.imageGhost.Domain.KeyStore;
import com.example.imageGhost.Utils.ServerMetaInfoGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;

@SpringBootApplication
public class ImageGhostApplication {
	public static void main(String[] args) {
		SpringApplication.run(ImageGhostApplication.class, args);

		// 서버 메모리 상에 비대칭키 키페어 올려두기
		HashMap<String, String> keyPair = ServerMetaInfoGenerator.generateAsymmetricKeyPair();
		String publicKey = keyPair.get("publicKey");
		String privateKey = keyPair.get("privateKey");
		// 설정 파일에 등록
		Config.setServerPublicKey(publicKey);
		Config.setServerPrivateKey(privateKey);

		/*
			서버 public key 는 디비에 등록할 필요 없음.
			서버 재부팅시마다 리셋되기 때문에 디비에 넣어도 어차피 다시 변경해줘야함.
		 */
		ServerMetaInfoGenerator.PUBLIC_KEY_LIST.add(publicKey); // 서버 public key 등록
	}
}
