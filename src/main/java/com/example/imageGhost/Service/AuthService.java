package com.example.imageGhost.Service;

import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Base64;

@Service
public class AuthService {

    private static ArrayList<String> authenticatedUserList = new ArrayList<>(); // 인증된 유저 목록

    /*
        plainText -> Cipher
        서버 측에서 사용하는 사용자 인증용 정답 제조기
     */
    public String encryptPlainText(String plainText, String stringPublicKey){
        String encryptedData = null;
        try{
            // Public key 만들기
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] bytePublicKey = Base64.getDecoder().decode(stringPublicKey.getBytes());
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(bytePublicKey);
            PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

            // 암호화 모드 설정
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(cipher.ENCRYPT_MODE, publicKey);

            // plainText 암호화
            byte[] byteEncryptedData = cipher.doFinal(plainText.getBytes());
            encryptedData = Base64.getEncoder().encodeToString(byteEncryptedData);
        }catch(Exception e){
            e.printStackTrace();
        }
        return encryptedData;
    }

    /*
        CipherText -> PlainText
        클라이언트로부터 전달받은 정답지 해독
        -> 사용자 측에서는 정답지를 보낼때 본인의 private Key 로 난독화해서 보내기.
     */
    public String decryptCipherText(String cipherText, String publicKey){
        return new String();
    }

    /*
        인증된 유저로 등록.
     */
    public String registerAsAuthenticatedUser(String userPublicKey){
        authenticatedUserList.add(userPublicKey);
        return userPublicKey;
    }

    /*
        유저 인증 여부 판별
     */
    public boolean isAuthenticatedUser(String userPublicKey){
        return authenticatedUserList.contains(userPublicKey);
    }
}
