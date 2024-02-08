package com.home_school.login.service;

import com.home_school.login.dto.GoogleOAuthRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class GoogleLoginService {

    @Value("${google.auth.url}")
    private String googleAuthUrl;

    @Value("${google.login.url}")
    private String googleLoginUrl;

    @Value("${google.client.id}")
    private String googleClientId;

    @Value("${google.redirect.url}")
    private String googleRedirectUrl;

    @Value("${google.secret}")
    private String googleClientSecret;

    public HttpHeaders requestForAuthCode(){
        //google로그인 페이지 요청 url
        String reqUrl = googleLoginUrl
                + "/o/oauth2/v2/auth?client_id="
                + googleClientId
                + "&redirect_uri="
                + googleRedirectUrl
                + "&response_type=code&scope=email%20profile%20openid&access_type=offline";

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(reqUrl));
        return headers;
    }

    public GoogleOAuthRequest requestForGetToken(String authCode){
        //받아온 인증코드를 보내 토큰을 얻음
        GoogleOAuthRequest googleOAuthRequest = GoogleOAuthRequest
                .builder()
                .clientId(googleClientId)
                .clientSecret(googleClientSecret)
                .code(authCode)
                .redirectUri(googleRedirectUrl)
                .grantType("authorization_code")
                .build();

        return googleOAuthRequest;
    }

    public String requestForUserInfo(String googleToken){
        //유저정보 얻기 위해 구글에 보낼 토큰포함 url 작성
        String requestUrl = UriComponentsBuilder
                .fromHttpUrl(googleAuthUrl + "/tokeninfo")
                .queryParam("id_token", googleToken)
                .toUriString();
        return requestUrl;
    }

}
