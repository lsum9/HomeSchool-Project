package com.home_school.login.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

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

    public String getTokenFromCookie(HttpServletRequest request) throws JsonProcessingException {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    return tokenProvider.userCodeFromToken(cookie.getValue());
                }
            }
        }
        return null;
    }
}
