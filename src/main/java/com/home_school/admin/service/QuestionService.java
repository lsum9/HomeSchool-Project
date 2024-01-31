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
public class QuestionService {
    private final QuestionMapper questionMapper;
    private final QuestionScriptMapper questionScriptMapper;
    private final Paging paging;

    public List<QuestionDto> readQuestion(PagingVo pagingVo){
        //총 로우수 pagingVo에 추가
        pagingVo.setTotalRow(questionCnt(pagingVo));
        //가공된 키워드, 현재페이지, 총 로우 수 담긴 pagingVo 페이징 클래스로 넘기기
        pagingVo=paging.pagingInfo(pagingVo);
        //System.out.println("서비스 페이징"+pagingVo);
        return questionMapper.readQuestion(pagingVo);
    }

    //총 글개수
    public int questionCnt(PagingVo pagingVo){
        return questionMapper.questionCnt(pagingVo);
    }
    public int createQuestion(QuestionDto questionDto){
        //지문이 있을 경우 가져오기
        return questionMapper.createQuestion(questionDto);
    }
    public int deleteQuestion(int questionNo){
        return questionMapper.deleteQuestion(questionNo);
    }

    public int updateQuestion(QuestionDto questionDto){
        return questionMapper.updateQuestion(questionDto);
    }

    //quesitonScript
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
