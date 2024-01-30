package com.home_school.admin.mapper;

import com.home_school.admin.dto.ExamDto;
import com.home_school.admin.dto.ExamQuestionDto;
import com.home_school.util.paging.PagingVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExamMapper {
    List<ExamDto> readExamList(PagingVo pagingVo);
    int examCnt(PagingVo pagingVo);
    int createExam(ExamDto examDto);

    int createExamQuestion(ExamDto examDto);
    int deleteExam(Long examNo);
    int updateExam(ExamDto examDto);

    //시험-문항 테이블 update
    int updateExamQuestion(ExamDto examDto);

    int readExamQcnt(Long examNo);

    int readExamTotPoint(Long examNo);

}
