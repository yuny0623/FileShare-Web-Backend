package com.example.imageGhost.Utils;

import lombok.Getter;
import org.apache.catalina.Server;

import java.security.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;

@Getter
public class ServerMetaInfoGenerator {

    public static ArrayList<String> publicKeyList = new ArrayList<>(); // public key 리스트
    private static final int KEY_SIZE = 2048;
    private static String serverPublicKey;
    private static String serverPrivateKey;
    
    /*
        서버 전용 비대칭키 키페어 생성 - SpringBoot application 구동 실행됨
     */
    public static HashMap<String, String> generateAsymmetricKeyPair(){
        HashMap<String, String> keyPairHashMap = new HashMap<>();
        try {
            SecureRandom secureRandom = new SecureRandom();
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(KEY_SIZE, secureRandom);
            KeyPair keyPair = keyPairGenerator.genKeyPair();

            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            String stringPublicKey = Base64.getEncoder().encodeToString(publicKey.getEncoded());
            String stringPrivateKey = Base64.getEncoder().encodeToString(privateKey.getEncoded());

            ServerMetaInfoGenerator.serverPrivateKey = stringPrivateKey;
            ServerMetaInfoGenerator.serverPublicKey = stringPublicKey;

            keyPairHashMap.put("publicKey", stringPublicKey);
            keyPairHashMap.put("privateKey", stringPrivateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return keyPairHashMap;
    }
}
