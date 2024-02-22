package com.home_school.login.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.home_school.login.common.UserAuthorize;
import com.home_school.login.dto.SignDto;
import com.home_school.login.security.CookieUtil;
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


    //추가정보 입력창
    @GetMapping("/sign-up-form")
    public ModelAndView signUpForm(HttpServletRequest request){
        System.out.println(Arrays.toString(request.getCookies()));
        //request.getHeaders().get("jwt-token");
        ModelAndView mav = new ModelAndView("sign-up-form");
        //String userId = loginService.idByCode(user.getUsername());
        //mav.addObject("loginUserDto", userId);
        return mav;
    }

    //추가정보 입력하여 가입 + 토큰 재발급
    @PostMapping("/sign-up")
    public ResponseEntity<Integer> signUp(@ModelAttribute SignDto signDto
                                        ,@AuthenticationPrincipal User user
                                        ){
        String userCode = user.getUsername();
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
