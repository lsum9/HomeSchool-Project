package com.home_school.login.controller;

import com.home_school.login.dto.*;
import com.home_school.login.security.CookieUtil;
import com.home_school.login.service.LoginService;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@RestController
@RequiredArgsConstructor
public class GoogleLoginController {

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
    @Value("${home.url}")
    private String homeSchoolUrl;

    private final LoginService loginService;
    private final CookieUtil cookieUtil;
    @GetMapping(value = "/login")
    public ResponseEntity<?> loginPage() throws Exception{

        String reqUrl = googleLoginUrl
                + "/o/oauth2/v2/auth?client_id="
                + googleClientId
                + "&redirect_uri="
                + googleRedirectUrl
                + "&response_type=code&scope=email%20profile%20openid&access_type=offline";

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(reqUrl));

        //1.reqUrl 구글로그인 창을 띄우고, 로그인 후 /login/oauth_google_check 으로 리다이렉션하게 한다.
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    //토큰발급을 위한 구글로그인 리디렉션
    @GetMapping(value = "/login/oauth_google_check")
    public ResponseEntity<Void> googleCheck(@RequestParam(value = "code") String authCode
                            ) throws Exception{

        //토큰을 얻기 위해 인증코드를 포함한 요청 작성
        GoogleOAuthRequest googleOAuthRequest = GoogleOAuthRequest
                .builder()
                .clientId(googleClientId)
                .clientSecret(googleClientSecret)
                .code(authCode)
                .redirectUri(googleRedirectUrl)
                .grantType("authorization_code")
                .build();

        WebClient webClient = WebClient.create();

        //토큰을 얻어오기 위한 요청 보내기
        GoogleLoginResponse googleLoginResponse = webClient
                .post()
                .uri(googleAuthUrl + "/token")
                .bodyValue(googleOAuthRequest)
                .retrieve()
                .bodyToMono(GoogleLoginResponse.class)
                .block();

        String googleToken = googleLoginResponse.getId_token();

        //5.받은 토큰을 구글에 보내 유저정보 얻기 위한 url 작성
        String requestUrl = UriComponentsBuilder
                .fromHttpUrl(googleAuthUrl + "/tokeninfo")
                .queryParam("id_token", googleToken)
                .toUriString();

        //토큰포함 정보를 보내 유저정보 받아오기
        GoogleTokenResponse googleTokenResponse = webClient
                .get()
                .uri(requestUrl)
                .retrieve()
                .bodyToMono(GoogleTokenResponse.class)
                .block();

        SignDto signDto = new SignDto();
        signDto.setUserCode(googleTokenResponse.getSub());
        signDto.setUserName(googleTokenResponse.getName());
        signDto.setUserId(googleTokenResponse.getEmail());

        //가입여부에 따른 처리 결과 signMap
        // 1. 리디렉 url 구성(신규유저: /sign-up-form, 기존유저: /main)
        // 2. 미가입시 인서트
        // 3. 토큰발급
        Map<String, String> signMap = new HashMap<>();
        signMap = loginService.sign(signDto);

        // 헤더에 토큰을 추가할 쿠키 생성
        String token = signMap.get("token");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Set-Cookie", "token=" + token + "; Path=/; Max-Age=3600");
        URI redirectUri = URI.create(homeSchoolUrl + signMap.get("url")); // 리다이렉트할 페이지의 URI 설정
        // 리다이렉트 응답을 생성하여 반환
        return ResponseEntity.status(HttpStatus.FOUND)
                .headers(headers)
                .location(redirectUri)
                .build();

        /*
        headers.setLocation(redirectUri);
        return new ResponseEntity<>(headers, HttpStatus.FOUND);*/
    }
}
