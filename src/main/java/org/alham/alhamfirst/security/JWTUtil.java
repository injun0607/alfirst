package org.alham.alhamfirst.security;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

//@Component
public class JWTUtil {
    /*
     * 기본적으로 JWT 토큰을 생생할때 비밀키가 필요한데, 보안과 호환성을 위해 일반문자열을 Encoding한 키로 사용한다.
     * 그후 SecretKey객체로 변경할때는 Encoding된 문자열을 Decoders.BASE64.decode()로 디코딩한후 사용한다
     */
    private final String SECRET_KEY = "AlhamAndSeoHee";
    private final SecretKey KEY = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());


    private SecretKey getSigningKey() {
        String encoded = Base64.getEncoder().encodeToString(SECRET_KEY.getBytes());
        byte[] keyBytes = Decoders.BASE64.decode(encoded);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * JWT 토큰 생성
     * @param id
     * @param subject
     * @return
     */
    public String generateToken(String id, String subject) {
        Date now = new Date();
        return Jwts.builder()
                .id(id)
                .subject(subject)
                .issuedAt(now)
                .signWith(getSigningKey())
                .expiration(new Date(now.getTime() + 1000 * 60 * 60 * 10)) // 10시간
                .compact();
    }

    public String getSubject(String token) {
        return extractAllClaims(token).getSubject();
    }

    // 토큰 검증
    public Boolean validateToken(String token, String username) {
        return getSubject(token).equals(username) && !isTokenExpired(token);
    }
    public boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }
    // 내부 메서드: Claims(payload 추출)
    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();
    }

}
