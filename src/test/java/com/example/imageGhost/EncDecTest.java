package com.example.imageGhost;

import com.example.imageGhost.Service.AuthService;
import com.example.imageGhost.Utils.ServerMetaInfoGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class EncDecTest {

    /*
		public key < - > private key 역방향 테스트
	 */

    @Test
    void 비대칭키_역방향_테스트1_공개키에서_비밀키(){
        /*
            public key로 암호화한 plainText를 private key로 복호화할 수 있는지 테스트
         */
        // given
        HashMap<String, String> keyPair = ServerMetaInfoGenerator.generateAsymmetricKeyPair();
        String publicKey = keyPair.get("publicKey");
        String privateKey = keyPair.get("privateKey");
        String plainText = "ImageGhost";

        // when
        String cipherText = AuthService.encryptPlainTextWithPublicKey(plainText, publicKey); // public key로 암호화
        String receivedPlainText = AuthService.decryptCipherTextWithPrivateKey(cipherText, privateKey); // private key로 복호화

        // then
        Assertions.assertEquals(receivedPlainText, plainText);
    }

    @Test
    void 비대칭키_역방향_테스트2_비밀키에서_공개키(){
        // given
        HashMap<String, String> keyPair = ServerMetaInfoGenerator.generateAsymmetricKeyPair();
        String publicKey = keyPair.get("publicKey");
        String privateKey = keyPair.get("privateKey");
        String plainText = "ImageGhost";

        // when
        String cipherText = AuthService.encryptPlainTextWithPrivateKey(plainText, privateKey); // public key로 암호화
        String receivedPlainText = AuthService.decryptCipherTextWithPublicKey(cipherText, publicKey); // private key로 복호화

        // then
        Assertions.assertEquals(receivedPlainText, plainText);
    }

    @Test
    void 비대칭키_키페어_생성_테스트(){

    }

    @Test
    void 대칭키_생성_테스트(){

    }
}
