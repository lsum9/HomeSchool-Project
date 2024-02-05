package com.home_school.admin.mapper;

import com.home_school.admin.dto.UserDto;
import com.home_school.util.paging.PagingVo;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
@Mapper
public interface UserMapper {
    List<UserDto> readUserList(PagingVo pagingVo);

    int userCnt(PagingVo pagingVo);
    int deleteUser(int userNo);
    int updateUser(UserDto userDto);

}
