package com.home_school.admin.service;

import com.home_school.admin.dto.UserDto;
import com.home_school.admin.mapper.UserMapper;
import com.home_school.util.paging.Paging;
import com.home_school.util.paging.PagingVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final Paging paging;

    //유저목록 가져오기
    @Transactional(readOnly = true)
    public List<UserDto> readUserList(PagingVo pagingVo){
        //총 로우수 pagingVo에 추가
        pagingVo.setTotalRow(userCnt(pagingVo));
        //가공된 키워드, 현재페이지, 총 로우 수 담긴 pagingVo 페이징 클래스로 넘기기
        pagingVo=paging.pagingInfo(pagingVo);
        return userMapper.readUserList(pagingVo);
    }

    @Transactional(readOnly = true)
    public int userCnt(PagingVo pagingVo){
        pagingVo=paging.pagingInfo(pagingVo);
        return userMapper.userCnt(pagingVo);
    }

    @Transactional
    public int deleteUser(int userNo){
        return userMapper.deleteUser(userNo);
    }

    @Transactional
    public int updateUser(UserDto userDto){
        return userMapper.updateUser(userDto);
    }
}
