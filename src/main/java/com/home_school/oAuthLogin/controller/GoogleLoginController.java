package com.home_school.oAuthLogin.controller;

import com.home_school.oAuthLogin.dto.GoogleLoginResponse;
import com.home_school.oAuthLogin.dto.GoogleOAuthRequest;
import com.home_school.oAuthLogin.service.GoogleLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


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
    private final GoogleLoginService googleLoginService;
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

    @GetMapping(value = "/login/oauth_google_check")
    public String googleCheck(@RequestParam(value = "code") String authCode
                             ) throws Exception{


        //받아온 인증코드를 보내 토큰을 얻음
        GoogleOAuthRequest googleOAuthRequest = GoogleOAuthRequest
                .builder()
                .clientId(googleClientId)
                .clientSecret(googleClientSecret)
                .code(authCode)
                .redirectUri(googleRedirectUrl)
                .grantType("authorization_code")
                .build();

        //토큰을 얻어오기 위한 요청 작성
       /* GoogleOAuthRequest googleOAuthRequest = googleLoginService.requestForGetToken(authCode);
        WebClient webClient = WebClient.create();
        System.out.println("토큰요청 : "+googleOAuthRequest);*/

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

        //5.받은 토큰을 구글에 보내 유저정보 얻기
        String requestUrl = UriComponentsBuilder
                .fromHttpUrl(googleAuthUrl + "/tokeninfo")
                .queryParam("id_token", googleToken)
                .toUriString();
        //String requestUrl = googleLoginService.requestForUserInfo(googleToken);

        //토큰포함 정보를 보내 유저정보 받아오기
         String jsonReturn = webClient
                .get()
                .uri(requestUrl)
                 .retrieve()
                .bodyToMono(String.class)
                .block();

         return jsonReturn;
    }

}
