package com.home_school.login.controller;

import com.home_school.admin.dto.TokenDto;
import com.home_school.login.common.UserAuthorize;
import com.home_school.login.dto.LoginUserDto;
import com.home_school.login.security.CookieUtil;
import com.home_school.login.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;

import java.net.URI;

@Log4j2
@RestController
@RequiredArgsConstructor
public class SignController {
    @Value("${home.url}")
    private String homeSchoolUrl;

    private final LoginService loginService;
    private final CookieUtil cookieUtil;

    @PostMapping("/signCheck")
    public ModelAndView signCheck(@RequestBody LoginUserDto loginUserDto){
        log.info(loginUserDto);
        ModelAndView mav = new ModelAndView(loginService.signCheck(loginUserDto));
        mav.addObject("loginUserDto",loginUserDto);
        return mav;
    }

    //추가정보 입력창
    @GetMapping("/sign-up-form")
    public ModelAndView signUpForm(){
        ModelAndView mav = new ModelAndView("sign-up-form");
        //mav.addObject("loginUserDto", loginUserDto);
        return mav;
    }


    //로그인-회원가입 절차
    @UserAuthorize
    @PostMapping
    public void sign(LoginUserDto loginUserDto){
        //기존유저, 신규유저 각각 다른 url로 보내기
        //유저 유형에 따라 signUrl 달리 구성
        String signUrl = loginService.signCheck(loginUserDto);

        WebClient webClient = WebClient.create();

        /*webClient.post()
                .uri(homeSchoolUrl+signUrl)
                .bodyValue(loginUserDto)
                .retrieve()
                .bodyToMono(Void.class) // 반환 값이 필요 없는 경우에는 Void.class를 사용
                .subscribe(); // 요청을 비동기적으로 보냄*/

        webClient.get()
                .uri(homeSchoolUrl+signUrl)
                .retrieve()
                .bodyToMono(Void.class) // 반환 값이 필요 없는 경우에는 Void.class를 사용
                .subscribe(); // 요청을 비동기적으로 보냄
    }


}
