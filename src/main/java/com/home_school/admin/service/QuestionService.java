package com.home_school.admin.service;

import com.home_school.admin.dto.QuestionDto;
import com.home_school.admin.dto.QuestionScriptDto;
import com.home_school.admin.mapper.QuestionMapper;
import com.home_school.admin.mapper.QuestionScriptMapper;
import com.home_school.util.paging.Paging;
import com.home_school.util.paging.PagingVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionMapper questionMapper;
    private final QuestionScriptMapper questionScriptMapper;
    private final Paging paging;

    @Transactional(readOnly = true)
    public List<QuestionDto> readQuestionList(PagingVo pagingVo){
        //총 로우수 pagingVo에 추가
        pagingVo.setTotalRow(questionCnt(pagingVo));
        //가공된 키워드, 현재페이지, 총 로우 수 담긴 pagingVo 페이징 클래스로 넘기기
        pagingVo=paging.pagingInfo(pagingVo);
        return questionMapper.readQuestionList(pagingVo);
    }

    //총 글개수
    @Transactional(readOnly = true)
    public int questionCnt(PagingVo pagingVo){
        pagingVo=paging.pagingInfo(pagingVo);
        return questionMapper.questionCnt(pagingVo);
    }

    @Transactional
    public int createQuestion(QuestionDto questionDto){
        //지문이 있을 경우 가져오기
        return questionMapper.createQuestion(questionDto);
    }
    @Transactional
    public int deleteQuestion(int questionNo){
        return questionMapper.deleteQuestion(questionNo);
    }

    @Transactional
    public int updateQuestion(QuestionDto questionDto){
        return questionMapper.updateQuestion(questionDto);
    }


    /////////////////quesitonScript
    @Transactional(readOnly = true)
    public List<QuestionScriptDto> readQuestionScript(PagingVo pagingVo){
        //총 로우수 pagingVo에 추가
        pagingVo.setTotalRow(questionScriptCnt(pagingVo));
        //가공된 키워드, 현재페이지, 총 로우 수 담긴 pagingVo 페이징 클래스로 넘기기
        pagingVo=paging.pagingInfo(pagingVo);
        return questionScriptMapper.readQuestionScript(pagingVo);
    }

    //총 글개수
    @Transactional(readOnly = true)
    public int questionScriptCnt(PagingVo pagingVo){
        pagingVo=paging.pagingInfo(pagingVo);
        return questionScriptMapper.questionScriptCnt(pagingVo);
    }
    @Transactional
    public int createQuestionScript(QuestionScriptDto questionScriptDto){
        return questionScriptMapper.createQuestionScript(questionScriptDto);
    }
    @Transactional
    public int deleteQuestionScript(int scriptNo){
        return questionScriptMapper.deleteQuestionScript(scriptNo);
    }

    @Transactional
    public int updateQuestionScript(QuestionScriptDto questionScriptDto){
        return questionScriptMapper.updateQuestionScript(questionScriptDto);
    }
}
