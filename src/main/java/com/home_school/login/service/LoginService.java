package com.home_school.login.service;

import com.home_school.admin.dto.TokenDto;
import com.home_school.login.dto.LoginUserDto;
import com.home_school.login.mapper.LoginMapper;
import com.home_school.login.mapper.RefreshTokenMapper;
import com.home_school.login.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@Service
@RequiredArgsConstructor
public class LoginService {
    private final LoginMapper loginMapper;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenMapper refreshTokenMapper;

    //가입여부 확인
    public String signCheck(LoginUserDto loginUserDto){
        log.info(loginUserDto);
        Map<String, String> map = new HashMap<>();
        String url;
        //가입여부 확인
        int signCheck = loginMapper.signCheck(loginUserDto);
        if(signCheck == 0){
            //미가입자라면 인서트++++++++분리?
            loginMapper.signUp(loginUserDto);
            //미가입자라면 회원가입 폼으로 이동
            url = "/sign-up-form";
        }else{
            //가입자라면 로그인 폼으로 이동
            //정식가입자 조건 추가 필요 **************************************************************************
            url = "/main";
        }
        return url;
    }//signCheck() end

    //user토큰 발급
    public String tokenMake(LoginUserDto loginUserDto){
        log.info(loginUserDto);
        //토큰 발급
        String token = tokenProvider.createToken(String.format("%s:%s", loginUserDto.getUserCode(), loginUserDto.getUserType()));

        //tokenDto에 토큰정보 세팅
        TokenDto tokenDto = new TokenDto();
        tokenDto.setAccessToken(token);
        //리프레시 토큰이 이미 있을 경우 토큰을 갱신하고 없을 경우 토큰 추가
        String registRefreshToken = refreshTokenMapper.selectRefreshToken(loginUserDto.getSub());
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

    //회원가입 절차
    public LoginUserDto signUp(LoginUserDto loginUserDto){

        //db에 sub, name, email, user_type 등록
        int signUpCnt = loginMapper.signUp(loginUserDto);
        //profile 등록
        loginMapper.insertProfile(loginUserDto);

        if(signUpCnt ==1){
            System.out.println("가입완료");
            return loginUserDto;
        }else{
            return null;
        }

    }//signUp end

    //로그인절차
    public TokenDto login(LoginUserDto loginUserDto){
        System.out.println("로그인절차 시작");

        TokenDto tokenDto = new TokenDto();
        //userCode, userType으로 토큰발급
        String accessToken = tokenProvider.createToken(String.format("%s:%s", loginUserDto.getUserCode(), loginUserDto.getUserType()));
        tokenDto.setAccessToken(accessToken);
        //리프레시 토큰이 이미 있을 경우 토큰을 갱신하고 없을 경우 토큰 추가
        String registedRefreshToken = refreshTokenMapper.selectRefreshToken(loginUserDto.getUserCode());

        tokenDto.setRefreshToken(tokenProvider.createRefreshToken());
        tokenDto.setUserCode(loginUserDto.getUserCode());
        if(registedRefreshToken == null){
            //리프레시 토큰 발급
            refreshTokenMapper.insertRefreshToken(tokenDto);
        }else{
            //리프레시 토큰 갱신
            refreshTokenMapper.updateRefreshToken(tokenDto);
        }//if end
        return tokenDto;
    }
}
