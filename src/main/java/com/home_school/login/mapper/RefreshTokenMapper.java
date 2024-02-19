package com.home_school.login.mapper;

import com.home_school.admin.dto.TokenDto;
import com.home_school.login.dto.LoginUserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RefreshTokenMapper {

    String selectRefreshToken(String userId);
    int insertRefreshToken(TokenDto tokenDto);

    int updateRefreshToken(TokenDto tokenDto);
}
