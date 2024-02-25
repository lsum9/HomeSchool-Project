package com.home_school.teacher.service;

import com.home_school.teacher.dto.TestDto;
import com.home_school.teacher.mapper.TestMapper;
import com.home_school.util.paging.Paging;
import com.home_school.util.paging.PagingVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        int testNo = testMapper.createTest(testDto);
        List<Map<String,Long>> testTargetList = testDto.getTestTarget();
        System.out.println(testDto.getTestNo());
        for(Map<String,Long> testTarget: testTargetList){
            testTarget.put("testNo",testDto.getTestNo());
        }
        testMapper.createTestTarget(testDto.getTestTarget());
        return testNo;
    }
    @Transactional
    public int deleteTest(Long testNo){
        //타겟 목록 지우기
        testMapper.deleteTestTarget(testNo);
        return testMapper.deleteTest(testNo);
    }

    @Transactional
    public int updateTest(TestDto testDto){
        //구 시험 타겟 데이터 저장
        List<Map<String,Long>> oldTargetList= new ArrayList<>();
        oldTargetList = testMapper.selectTargetList(testDto.getTestNo());

        //새 시험 타겟 데이터
        List<Map<String,Long>> newTargetList= new ArrayList<>();
        newTargetList = testDto.getTestTarget();
        for(Map<String,Long> testTarget: newTargetList){
            testTarget.put("testNo",testDto.getTestNo());
        }

        //타겟 목록이 있고 이전 목록과 다르다면 이전 목록 삭제 후 인서트
        if(newTargetList!=oldTargetList && !newTargetList.isEmpty()){
            testMapper.deleteTestTarget(testDto.getTestNo());
            testMapper.createTestTarget(newTargetList);
        }
        return testMapper.updateTest(testDto);
    }

}
