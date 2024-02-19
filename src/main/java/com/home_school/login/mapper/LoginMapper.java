package com.home_school.login.mapper;

import com.home_school.login.dto.LoginUserDto;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {

    //구글sub로 가입여부 체크
    int signCheck(LoginUserDto loginUserDto);

    int signUp(LoginUserDto loginUserDto);

    int insertProfile(LoginUserDto loginUserDto);

}
