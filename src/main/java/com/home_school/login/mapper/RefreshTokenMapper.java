package com.home_school.login.mapper;

import com.home_school.login.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RefreshTokenMapper {

    String selectRefreshToken(String userId);
    int insertRefreshToken(UserDto userDto);

    int updateRefreshToken(UserDto userDto);

}
