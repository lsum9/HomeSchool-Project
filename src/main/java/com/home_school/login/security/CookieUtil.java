package com.home_school.login.security;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Log4j2
@Component
@RequiredArgsConstructor
public class CookieUtil {
    private final TokenProvider tokenProvider;
    public void addTokenToCookie(String tokenValue) {
        //실행중인 스레드의 response가져오기
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

        if (response == null) {
            throw new IllegalStateException("Unable to retrieve HttpServletResponse");
        }

        // JWT 토큰을 쿠키에 담아 클라이언트에게 전송
        Cookie cookie = new Cookie("jwt-token", tokenValue);
        cookie.setMaxAge(60 * 60); // 쿠키의 만료 시간 설정
        cookie.setPath("/"); // 쿠키의 경로 설정

        response.addCookie(cookie);
    }

    public String tokenFromCookie(HttpServletRequest request){
        String token = null;
        Cookie[] cookies=request.getCookies(); // 모든 쿠키 가져오기
        if(cookies!=null){
            for (Cookie c : cookies) {
                String name = c.getName(); // 쿠키 이름 가져오기
                String value = c.getValue(); // 쿠키 값 가져오기
                if(name.equals("jwt-token")){
                    token=value;
                }else{
                    throw new RuntimeException("발급된 jwt토큰 없음");
                }
            }
        }
        return token;
    }
}
