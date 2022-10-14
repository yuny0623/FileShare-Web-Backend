package com.example.imageGhost;

import com.example.imageGhost.Service.AuthService;
import com.example.imageGhost.Utils.ServerMetaInfoGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class EncDecKeyTest {

    @Test
    void 비대칭키_역방향_테스트1_공개키에서_비밀키(){
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
    void 비대칭키_키페어_생성_테스트(){
        //given
        HashMap<String, String> keyPair = ServerMetaInfoGenerator.generateAsymmetricKeyPair();

        // when
        String publicKey = keyPair.get("publicKey");

        //then
        Assertions.assertEquals(publicKey, ServerMetaInfoGenerator.SERVER_PUBLIC_KEY);
    }

}
