package org.alham.alhamfirst.security.oauth;

import lombok.RequiredArgsConstructor;
import org.alham.alhamfirst.security.JWTUtil;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuth2ServiceImpl implements OAuth2Service{

//    private final JWTUtil jwtUtil;
//
//    @Override
//    public String getOAuth2UserInfo(String accessToken) {
//        // OAuth2를 통해 인증정보를 가져오는 메소드
//
//        //임시 토큰 발급
//        String oAuth2UserInfo = "testUserInfo";  // 예: JSON 형식의 사용자 정보
//        return oAuth2UserInfo;
//    }
//
//    @Override
//    public String createJwtTokenFromOAuth2(String oAuth2UserInfo) {
//        // JWT를 생성하여 반환
//
//        //TODO 현재 아이디랑,이름을 둘다 받고있지만 변경 필요할수도있음
//        return jwtUtil.generateToken(oAuth2UserInfo, oAuth2UserInfo);
//    }
}
