package org.alham.alhamfirst.util;

import org.alham.alhamfirst.common.error.AlhamCustomException;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AESUtil {
    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final String SECRET_KEY = "alhamFirst345666"; // 16바이트 키
    private static final String IV = "decryptAlham1234"; // 16바이트 IV

    public static String encrypt(String plainText) throws AlhamCustomException {
        try{
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            SecretKey secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            IvParameterSpec iv = new IvParameterSpec(IV.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
            byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException
                 | InvalidKeyException | InvalidAlgorithmParameterException
                 | IllegalBlockSizeException | BadPaddingException e) {
            throw new AlhamCustomException("AESUtil encrypt Error",e);
        }
    }

    public static String decrypt(String encryptedText) throws AlhamCustomException{

        try{
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            SecretKey secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            IvParameterSpec iv = new IvParameterSpec(IV.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
            return new String(decryptedBytes);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException
                 | InvalidKeyException | InvalidAlgorithmParameterException
                 | IllegalBlockSizeException | BadPaddingException e) {

            throw new AlhamCustomException("AESUtil decrypt Error",e);
        }


    }

}
