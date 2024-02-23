package com.home_school.teacher.mapper;

import com.home_school.teacher.dto.TestDto;
import com.home_school.util.paging.PagingVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TestMapper {
    List<TestDto> readTestList(PagingVo pagingVo);

    //List<TestDto> readTestDetail(Long TestNo);
    int testCnt(PagingVo pagingVo);
    int createTest(TestDto testDto);
    int createTestTarget(List<Map<String,Long>> testTarget);
    int deleteTest(Long testNo);
    int updateTest(TestDto testDto);
    
}
