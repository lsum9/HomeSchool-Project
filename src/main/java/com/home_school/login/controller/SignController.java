package com.home_school.login.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.home_school.login.common.UserAuthorize;
import com.home_school.login.dto.SignDto;
import com.home_school.login.security.CookieUtil;
import com.home_school.login.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Log4j2
@RestController
@RequiredArgsConstructor
public class SignController {
    @Value("${home.url}")
    private String homeSchoolUrl;
    private final CookieUtil cookieUtil;
    private final LoginService loginService;


    //추가정보 입력창
    @UserAuthorize
    @GetMapping("/sign-up-form")
    public ModelAndView signUpForm(){
        ModelAndView mav = new ModelAndView("sign-up-form");
        //mav.addObject("loginUserDto", loginUserDto);
        return mav;
    }

    //추가정보 입력하여 가입 + 토큰 재발급
    @PostMapping("/sign-up")
    public ResponseEntity<Integer> signUp(@ModelAttribute SignDto signDto, HttpServletRequest request) throws JsonProcessingException {
        String userCode = cookieUtil.getTokenFromCookie(request);
        log.info("추출유저코드: "+userCode);
        signDto.setUserCode(userCode);
        int cnt = loginService.signUpdate(signDto);
        
        //토큰 재발급해 쿠키 추가
        String token = loginService.tokenMake(signDto);
        cookieUtil.addTokenToCookie(token);
        return ResponseEntity.ok(cnt);
    }

    //로그인 후 메인페이지
    @GetMapping("/main")
    public ModelAndView mainPage(){
        ModelAndView mav = new ModelAndView("main");
        //mav.addObject("loginUserDto", loginUserDto);
        return mav;
    }

    //로그인-회원가입 절차
    /*
    @PostMapping
    public void sign(LoginUserDto loginUserDto){
        //기존유저, 신규유저 각각 다른 url로 보내기
        //유저 유형에 따라 signUrl 달리 구성
        String signUrl = loginService.signCheck(loginUserDto);

        WebClient webClient = WebClient.create();

        webClient.get()
                .uri(homeSchoolUrl+signUrl)
                .retrieve()
                .bodyToMono(Void.class) // 반환 값이 필요 없는 경우에는 Void.class를 사용
                .subscribe(); // 요청을 비동기적으로 보냄
    }*/
}