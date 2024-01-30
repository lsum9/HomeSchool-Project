/*
package com.home_school.admin.service;

import com.home_school.admin.dto.QuestionDto;
import com.home_school.admin.dto.QuestionScriptDto;
import com.home_school.admin.mapper.QuestionMapper;
import com.home_school.admin.mapper.QuestionScriptMapper;
import com.home_school.util.paging.Paging;
import com.home_school.util.paging.PagingVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionScriptService {
    private final QuestionScriptMapper questionScriptMapper;
    private final Paging paging;

    public List<QuestionScriptDto> readQuestionScript(PagingVo pagingVo){
        //총 로우수 pagingVo에 추가
        pagingVo.setTotalRow(questionScriptCnt(pagingVo));
        //가공된 키워드, 현재페이지, 총 로우 수 담긴 pagingVo 페이징 클래스로 넘기기
        pagingVo=paging.pagingInfo(pagingVo);
        //System.out.println("서비스 페이징"+pagingVo);
        return questionScriptMapper.readQuestionScript(pagingVo);
    }

    //총 글개수
    public int questionScriptCnt(PagingVo pagingVo){
        return questionScriptMapper.questionScriptCnt(pagingVo);
    }
    public int createQuestionScript(QuestionScriptDto questionScriptDto){
        return questionScriptMapper.createQuestionScript(questionScriptDto);
    }
    public int deleteQuestionScript(int scriptNo){
        return questionScriptMapper.deleteQuestionScript(scriptNo);
    }

    public int updateQuestionScript(QuestionScriptDto questionScriptDto){
        return questionScriptMapper.updateQuestionScript(questionScriptDto);
    }




}
*/
