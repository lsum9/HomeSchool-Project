package com.home_school.teacher.service;

import com.home_school.teacher.dto.ClassDto;
import com.home_school.teacher.mapper.ClassMapper;
import com.home_school.util.paging.Paging;
import com.home_school.util.paging.PagingVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ClassService {
    private final ClassMapper classMapper;
    private final Paging paging;

    //총 글개수
    @Transactional(readOnly = true)
    public int classCnt(PagingVo pagingVo){
        pagingVo=paging.pagingInfo(pagingVo);
        return classMapper.classCnt(pagingVo);
    }

    @Transactional(readOnly = true)
    public List<ClassDto> readClassList(PagingVo pagingVo){
        //총 로우수 pagingVo에 추가
        pagingVo.setTotalRow(classCnt(pagingVo));
        //가공된 키워드, 현재페이지, 총 로우 수 담긴 pagingVo 페이징 클래스로 넘기기
        pagingVo=paging.pagingInfo(pagingVo);
        System.out.println("서비스 페이징"+pagingVo);
        return classMapper.readClassList(pagingVo);
    }

    @Transactional(readOnly = true)
    public Map<String,Object> readClassDetail(Long classNo){
       // return classMapper.readClassDetail(classNo);
        return null;
    }

    @Transactional
    public int createClass(ClassDto ClassDto){
        return classMapper.createClass(ClassDto);
    }
    @Transactional
    public int deleteClass(Long ClassNo){
        return classMapper.deleteClass(ClassNo);
    }

    @Transactional
    public int updateClass(ClassDto ClassDto){
        return classMapper.updateClass(ClassDto);
    }




}
