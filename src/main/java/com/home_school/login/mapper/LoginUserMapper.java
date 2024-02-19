package com.home_school.login.mapper;

import com.home_school.login.dto.LoginUserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginUserMapper {

    LoginUserDto selectUserInfo(String userId);

    int deleteUser(String userId);

    int updateUser(LoginUserDto loginUserDto);

}
