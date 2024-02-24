package com.home_school.admin.service;

import com.home_school.admin.dto.*;
import com.home_school.admin.mapper.ExamMapper;
import com.home_school.admin.mapper.QuestionMapper;
import com.home_school.util.paging.Paging;
import com.home_school.util.paging.PagingVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExamService {

    private final ExamMapper examMapper;
    private final Paging paging;

    //시험개수
    public int examCnt(PagingVo pagingVo){
        pagingVo=paging.pagingInfo(pagingVo);
        return examMapper.examCnt(pagingVo);
    }

    //시험목록 가져오기
    @Transactional(readOnly = true)
    public List<ExamDto> readExamList(PagingVo pagingVo){
        //총 로우수 pagingVo에 추가
        pagingVo.setTotalRow(examCnt(pagingVo));
        //가공된 키워드, 현재페이지, 총 로우 수 담긴 pagingVo 페이징 클래스로 넘기기
        pagingVo=paging.pagingInfo(pagingVo);
        return examMapper.readExamList(pagingVo);
    }

    //시험 상세 가져오기
    @Transactional(readOnly = true)
    public Map<String, Object> readExamDetail(Long examNo){
        Map<String,Object> map = new HashMap<>();
        //시험 일반정보 가져오기
        ExamDto examDto = examMapper.readExamDetail(examNo);

        //시험-문제 관계 조인 테이블에서 문제, 문제순서, 해설 가져오기
        List<ExamQuestionDto> examQuestionDto = examMapper.readExamQuestionDetail(examNo);

        map.put("exam", examDto);
        map.put("examQuestion", examQuestionDto);

        return map;
    }

    //시험 insert
    //클라이언트에서 넘어온 시험일반정보+Map<문항순서, 문항번호>활용
    @Transactional
    public int createExam(ExamDto examDto){
        int createUpdateExamResult = 0;
        //시험생성
        examMapper.createExam(examDto);
        Long examNo = examDto.getExamNo();
        //시험-문제 테이블 경유하여 문항수, 총점 examNo에 등록
        examDto.setExamQcnt(examMapper.readExamQuestionCnt(examNo));
        examDto.setExamTotPoint(examMapper.readExamTotPoint(examNo));
        //위에서 받아온 문항수, 총점을 tb_exam에 update
        createUpdateExamResult = examMapper.updateExam(examDto);
        return createUpdateExamResult;
    }

    @Transactional
    public int deleteExam(Long examNo){
        return examMapper.deleteExam(examNo);
    }
    
    
    //시험 업데이트
    @Transactional
    public int updateExam(ExamDto examDto){

        //구 시험-문제 데이터 저장
        Map<Integer,Long> oldExamQuestionNo = new HashMap<>(); //기존 시험-문항 정보 저장할 map
        Map<Integer,Long> newExamQuestionNo = examDto.getExamQuestionNo(); //새 시험-문항 정보 map
        List<ExamQuestionDto> examQuestionDtoList = examMapper.readExamQuestionDetail(examDto.getExamNo());
        for(ExamQuestionDto examQuestionDto:examQuestionDtoList) {
            oldExamQuestionNo.put(examQuestionDto.getOrderNo(), examQuestionDto.getQuestionNo());
        }

        //변경된 문항정보가 들어올 경우
        //기존 시험-문항 데이터 삭제하고 새로 생성
        if(newExamQuestionNo != oldExamQuestionNo && !newExamQuestionNo.isEmpty()) {
            examMapper.deleteExamQuestion(examDto.getExamNo());
            examMapper.createExamQuestion(examDto);
        }else{
            examDto.setExamQuestionNo(oldExamQuestionNo);
        }
        //시험-문제 테이블 통해 새로 생성된 문항수, 총점 examDto에 넣기
        examDto.setExamQcnt(examMapper.readExamQuestionCnt(examDto.getExamNo()));
        examDto.setExamTotPoint(examMapper.readExamTotPoint(examDto.getExamNo()));
        //examDto넘겨 문항수, 총점 갱신
        return examMapper.updateExam(examDto);

    }
}
