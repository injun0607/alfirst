package org.alham.alhamfirst.util;

public class CommonUtil {

    public static Long getDecryptedId(String encryptedId) {
        return Long.parseLong(AESUtil.decrypt(encryptedId));
    }

    public static String getEncryptedId(Long id) {
        return AESUtil.encrypt(id.toString());
    }

}
