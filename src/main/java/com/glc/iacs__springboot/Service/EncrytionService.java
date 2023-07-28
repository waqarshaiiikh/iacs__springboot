package com.glc.iacs__springboot.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class EncrytionService {
    
    
    @Value("${encryption.secret-key}")
    private String secretKeyBase64;

    private SecretKey secretKey;

    @PostConstruct
    public void init() {
        try {
            byte[] decodedKey = Base64.getDecoder().decode(secretKeyBase64);
            this.secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
        } catch (Exception e) {
            // Log the exception or print the stack trace for troubleshooting
            e.printStackTrace();
            // Rethrow the exception to prevent the bean from being created
            throw new RuntimeException("Failed to initialize EncrytionService", e);
        }
    }

    // public EncrytionService (){
    //     byte[] decodedKey = Base64.getDecoder().decode(secretKeyBase64);
    //     this.secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    
    // }

    public String encrypt(String data) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, this.secretKey);
        byte[] encryptedData = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedData);
        
    }

    public String decrypt(String encryptedData) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, this.secretKey);
        byte[] decodedData = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedData = cipher.doFinal(decodedData);
        return new String(decryptedData, StandardCharsets.UTF_8);
    }
}
