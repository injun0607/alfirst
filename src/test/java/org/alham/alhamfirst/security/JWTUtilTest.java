package org.alham.alhamfirst.security;

import org.junit.jupiter.api.Test;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;


class JWTUtilTest {

    @Test
    void getSigningKey() {
        String key = "injun";
        byte[] encode = Base64.getEncoder().encode(key.getBytes());
        for (byte b : encode) {
            System.out.println(b);
        }

        byte[] decode = Base64.getDecoder().decode(encode);
        for (byte b : decode) {

            System.out.println(b);
        }

    }
}