package com.home_school.admin.mapper;

import com.home_school.admin.dto.QuestionDto;
import com.home_school.util.paging.PagingVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionMapper {
    List<QuestionDto> readQuestionList(PagingVo pagingVo);

    QuestionDto readQuestionDetail(Long questionNo);
    int questionCnt(PagingVo pagingVo);
    int createQuestion(QuestionDto questionDto);
    int deleteQuestion(int questionNo);
    int updateQuestion(QuestionDto questionDto);
    
}
