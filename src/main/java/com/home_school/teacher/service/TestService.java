package com.home_school.teacher.service;

import com.home_school.teacher.dto.TestDto;
import com.home_school.teacher.mapper.TestMapper;
import com.home_school.util.paging.Paging;
import com.home_school.util.paging.PagingVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TestService {
    private final TestMapper testMapper;
    private final Paging paging;

    //총 글개수
    @Transactional(readOnly = true)
    public int testCnt(PagingVo pagingVo){
        pagingVo=paging.pagingInfo(pagingVo);
        return testMapper.testCnt(pagingVo);
    }

    @Transactional(readOnly = true)
    public List<TestDto> readTestList(PagingVo pagingVo){
        //총 로우수 pagingVo에 추가
        pagingVo.setTotalRow(testCnt(pagingVo));
        //가공된 키워드, 현재페이지, 총 로우 수 담긴 pagingVo 페이징 클래스로 넘기기
        pagingVo=paging.pagingInfo(pagingVo);
        return testMapper.readTestList(pagingVo);
    }

/*@Transactional(readOnly = true)
    public Map<String,Object> readTestDetail(Long TestNo){
        return testMapper.readTestDetail(TestNo);
    }*/


    @Transactional
    public int createTest(TestDto testDto){
        //시험 생성 성공 시 시험 대상 인서트
        int cnt = testMapper.createTest(testDto);
        System.out.println(testDto.getTestTarget());
        testMapper.createTestTarget(testDto.getTestTarget());
        return cnt;
    }
    @Transactional
    public int deleteTest(Long testNo){
        return testMapper.deleteTest(testNo);
    }

    @Transactional
    public int updateTest(TestDto testDto){
        return testMapper.updateTest(testDto);
    }

}
