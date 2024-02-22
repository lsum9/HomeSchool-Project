package com.home_school.login.service;

import com.home_school.login.dto.TokenDto;
import com.home_school.login.dto.SignDto;
import com.home_school.login.mapper.LoginMapper;
import com.home_school.login.mapper.RefreshTokenMapper;
import com.home_school.login.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Log4j2
@Service
@RequiredArgsConstructor
public class LoginService {
    private final LoginMapper loginMapper;
    private final TokenProvider tokenProvider;
    private final RefreshTokenMapper refreshTokenMapper;


    //가입여부에 따른 처리
    // 1. 리디렉 url 구성
    // 2. 미가입시 인서트
    // 3. 토큰발급
    public Map<String, String> sign(SignDto signDto){
        Map<String, String> signMap = new HashMap<>();
        
        //가입여부 및 유저유형 확인
        SignDto selectUser = loginMapper.selectUser(signDto.getUserCode());
        if(!Objects.equals(selectUser.getUserType(), "USER")){
            signMap.put("url", "/main");
        } else if (selectUser.getUserType().equals("USER")) {
            //임시가입자라면 회원가입 폼으로 이동 url 세팅
            signMap.put("url", "/sign-up-form");
        }else{
            //미가입자라면 회원가입
            loginMapper.userInsert(signDto);
            //미가입자라면 회원가입 폼으로 이동 url 세팅
            signMap.put("url", "/sign-up-form");
        }
        //토큰추가
        signMap.put("token", tokenMake(selectUser));
        return signMap;
    }

    //user토큰 발급
    public String tokenMake(SignDto signDto){
        //토큰 발급
        String token = tokenProvider.createToken(String.format("%s:%s", signDto.getUserCode(), signDto.getUserType()));
        //tokenDto에 토큰정보 세팅
        TokenDto tokenDto = new TokenDto();
        tokenDto.setAccessToken(token);
        tokenDto.setUserCode(signDto.getUserCode());
        //리프레시 토큰이 이미 있을 경우 토큰을 갱신하고 없을 경우 토큰 추가
        String registRefreshToken = refreshTokenMapper.selectRefreshToken(tokenDto.getUserCode());
        tokenDto.setRefreshToken(tokenProvider.createRefreshToken());
        if(registRefreshToken != null){
            //리프레시 토큰 갱신
            refreshTokenMapper.updateRefreshToken(tokenDto);
        }else{
            //리프레시 토큰 발급
            refreshTokenMapper.insertRefreshToken(tokenDto);
        }
        //반영결과 리턴
        return token;
    }

    //추가정보입력창 가입아이디 노출
    public String idByCode(String userCode){
        return loginMapper.idByCode(userCode);
    }

    //추가정보 입력하여 최종 가입절차 진행
    public int signUpdate(SignDto signDto){
        return loginMapper.signUpdate(signDto);
    }
    
}
