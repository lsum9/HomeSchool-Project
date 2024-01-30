package com.home_school.admin.mapper;

import com.home_school.admin.dto.QuestionScriptDto;
import com.home_school.util.paging.PagingVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionScriptMapper {
    List<QuestionScriptDto> readQuestionScript(PagingVo pagingVo);

    int questionScriptCnt(PagingVo pagingVo);
    int createQuestionScript(QuestionScriptDto questionScriptDto);
    int deleteQuestionScript(int questionScriptNo);
    int updateQuestionScript(QuestionScriptDto questionScriptDto);
    
}
