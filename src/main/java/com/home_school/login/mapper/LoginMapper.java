package com.home_school.login.mapper;

import com.home_school.login.dto.SignDto;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {

    //구글sub로 가입여부 체크
    int signCheck(SignDto signDto);

    int userInsert(SignDto signDto);

    SignDto selectUser(String userCode);

    int signUpdate(SignDto signDto);

    String idByCode(String userCode);

    //int insertProfile(SignDto signDto);

}
