package com.home_school.teacher.mapper;

import com.home_school.teacher.dto.ClassDto;
import com.home_school.util.paging.PagingVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClassMapper{
    List<ClassDto> readClassList(PagingVo pagingVo);

    //List<ClassDto> readClassDetail(Long classNo);
    int classCnt(PagingVo pagingVo);
    int createClass(ClassDto classDto);
    int deleteClass(Long classNo);
    int updateClass(ClassDto classDto);
    
}
