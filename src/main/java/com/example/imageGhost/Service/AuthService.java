package com.example.imageGhost.Service;

import com.example.imageGhost.Repository.UserRepository;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import java.awt.desktop.UserSessionEvent;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.NoSuchElementException;

@Service
public class AuthService {

    private static ArrayList<String> authenticatedUserList = new ArrayList<>(); // 인증된 유저 목록
    private final UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    /*
        PlainText -> CipherText
        via public key
     */
    public static String encryptPlainTextWithPublicKey(String plainText, String stringPublicKey){
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
//
//    /*
//        PlainText -> CipherText
//        via private key
//     */
//    public static String encryptPlainTextWithPrivateKey(String plainText, String stringPrivateKey){
//        String encryptedData = null;
//        try{
//            // Public key 만들기
//            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//            byte[] bytePrivateKey = Base64.getDecoder().decode(stringPrivateKey.getBytes());
//            X509EncodedKeySpec privateKeySpec = new X509EncodedKeySpec(bytePrivateKey);
//            PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
//
//            // 암호화 모드 설정
//            Cipher cipher = Cipher.getInstance("RSA");
//            cipher.init(cipher.ENCRYPT_MODE, privateKey);
//
//            // plainText 암호화
//            byte[] byteEncryptedData = cipher.doFinal(plainText.getBytes());
//            encryptedData = Base64.getEncoder().encodeToString(byteEncryptedData);
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        return encryptedData;
//    }

    /*
        CipherText -> PlainText
        via private key
        클라이언트로부터 전달받은 정답지 해독
        -> 사용자 측에서는 정답지를 보낼때 본인의 private Key 로 난독화해서 보내기.
     */
    public static String decryptCipherTextWithPrivateKey(String encryptedData, String stringPrivateKey){
        String decryptedData = null;
        try {
            // Private Key 객체 생성
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] bytePrivateKey = Base64.getDecoder().decode(stringPrivateKey.getBytes());
            PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(bytePrivateKey);
            PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

            // 암호화 모드 설정
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            // 암호문 복호화
            byte[] byteEncryptedData = Base64.getDecoder().decode(encryptedData.getBytes());
            byte[] byteDecryptedData = cipher.doFinal(byteEncryptedData);
            decryptedData = new String(byteDecryptedData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptedData;
    }
//
//    /*
//        CipherText -> PlainText
//        via public key
//     */
//    public static String decryptCipherTextWithPublicKey(String encryptedData, String stringPublicKey){
//        String decryptedData = null;
//        try {
//            // Private Key 객체 생성
//            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//            byte[] bytePublicKey = Base64.getDecoder().decode(stringPublicKey.getBytes());
//            PKCS8EncodedKeySpec publicKeySpec = new PKCS8EncodedKeySpec(bytePublicKey);
//            PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
//
//            // 암호화 모드 설정
//            Cipher cipher = Cipher.getInstance("RSA");
//            cipher.init(Cipher.DECRYPT_MODE, publicKey);
//
//            // 암호문 복호화
//            byte[] byteEncryptedData = Base64.getDecoder().decode(encryptedData.getBytes());
//            byte[] byteDecryptedData = cipher.doFinal(byteEncryptedData);
//            decryptedData = new String(byteDecryptedData);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return decryptedData;
//    }

    /*
        인증된 유저로 등록.
     */
    public boolean registerAsAuthenticatedUser(String userPublicKey){
        try {
            userRepository.findByPublicKey(userPublicKey);
        }catch(NoSuchElementException e){
            e.printStackTrace();
            return false;
        }
        authenticatedUserList.add(userPublicKey);
        return true;
    }

    /*
        유저 인증 여부 판별
     */
    public boolean isAuthenticatedUser(String userPublicKey){
        try {
            userRepository.findByPublicKey(userPublicKey);
        }catch(NoSuchElementException e){
            e.printStackTrace();
            return false;
        }
        return authenticatedUserList.contains(userPublicKey);
    }
}
