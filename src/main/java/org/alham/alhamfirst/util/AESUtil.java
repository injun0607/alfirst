package org.alham.alhamfirst.util;

import org.alham.alhamfirst.common.exception.AlhamCustomException;
import org.springframework.http.HttpStatus;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AESUtil {
    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final String SECRET_KEY = "alhamFirst345666"; // 16바이트 키
    private static final String IV = "decryptAlham1234"; // 16바이트 IV

    public static String encrypt(String plainText) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            SecretKey secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            IvParameterSpec iv = new IvParameterSpec(IV.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
            byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);

        } catch (Exception e) {
            throw new AlhamCustomException(HttpStatus.NOT_FOUND, "암호화 중 오류가 발생했습니다.",e);
        }
    }

    public static String decrypt(String encryptedText){
        try{
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            SecretKey secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            IvParameterSpec iv = new IvParameterSpec(IV.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
            return new String(decryptedBytes);
        } catch (Exception e) {
            throw new AlhamCustomException(HttpStatus.NOT_FOUND, "복호화 중 오류가 발생했습니다.",e);
        }
    }

}
