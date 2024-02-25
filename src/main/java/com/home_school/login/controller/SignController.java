package com.home_school.login.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.home_school.login.common.UserAuthorize;
import com.home_school.login.dto.SignDto;
import com.home_school.login.security.CookieUtil;
import com.home_school.login.security.TokenProvider;
import com.home_school.login.service.LoginService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;


@Log4j2
@RestController
@RequiredArgsConstructor
public class SignController {
    @Value("${home.url}")
    private String homeSchoolUrl;
    private final CookieUtil cookieUtil;
    private final LoginService loginService;
    private final TokenProvider tokenProvider;


    //추가정보 입력창
    @GetMapping("/sign-up-form")
    public ModelAndView signUpForm(HttpServletRequest request) throws JsonProcessingException {
        //쿠키에 담긴 토큰으로부터 유저code 가져오기
        String userCode = tokenProvider.userCodeFromToken(cookieUtil.tokenFromCookie(request));
        ModelAndView mav = new ModelAndView("sign-up-form");

        //유저코드로 유저아이디 가져오기
        mav.addObject("signDto", loginService.infoByCode(userCode));
        return mav;
    }

    //추가정보 입력하여 가입 + 토큰 재발급
    @PostMapping("/sign-up")
    public ResponseEntity<Integer> signUp(@ModelAttribute SignDto signDto
                                        ,@AuthenticationPrincipal User user
                                        ,HttpServletRequest request) throws JsonProcessingException {
        /*String userCode = user.getUsername();
        System.out.println("sign-up userCode확인 : "+userCode);
        */
        //쿠키에 담긴 토큰으로부터 유저code 가져오기
        String userCode = tokenProvider.userCodeFromToken(cookieUtil.tokenFromCookie(request));
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
}
