package com.home_school.login.mapper;

import com.home_school.login.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    UserDto selectUserInfo(String userId);

    int deleteUser(String userId);

    int updateUser(UserDto userDto);

}
