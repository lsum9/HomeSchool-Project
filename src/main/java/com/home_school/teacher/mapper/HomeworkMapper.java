package com.home_school.teacher.mapper;

import com.home_school.teacher.dto.HomeworkDto;
import com.home_school.util.paging.PagingVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HomeworkMapper {
    List<HomeworkDto> readHomeworkList(PagingVo pagingVo);

    //List<HomeworkDto> readHomeworkDetail(Long homeworkNo);
    int HomeworkCnt(PagingVo pagingVo);
    int createHomework(HomeworkDto homeworkDto);
    int deleteHomework(Long homeworkNo);
    int updateHomework(HomeworkDto homeworkDto);
    
}
