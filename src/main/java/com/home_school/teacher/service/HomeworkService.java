/*
package com.home_school.teacher.service;

import com.home_school.teacher.dto.HomeworkDto;
import com.home_school.teacher.mapper.HomeworkMapper;
import com.home_school.util.paging.Paging;
import com.home_school.util.paging.PagingVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeworkService {
    private final HomeworkMapper homeworkMapper;
    private final Paging paging;

    //총 글개수
    @Transactional(readOnly = true)
    public int HomeworkCnt(PagingVo pagingVo){
        pagingVo=paging.pagingInfo(pagingVo);
        return homeworkMapper.HomeworkCnt(pagingVo);
    }

    @Transactional(readOnly = true)
    public List<HomeworkDto> readHomeworkList(PagingVo pagingVo){
        //총 로우수 pagingVo에 추가
        pagingVo.setTotalRow(HomeworkCnt(pagingVo));
        //가공된 키워드, 현재페이지, 총 로우 수 담긴 pagingVo 페이징 클래스로 넘기기
        pagingVo=paging.pagingInfo(pagingVo);
        return homeworkMapper.readHomeworkList(pagingVo);
    }

    */
/*@Transactional(readOnly = true)
    public Map<String,Object> readHomeworkDetail(Long HomeworkNo){
        return HomeworkMapper.readHomeworkDetail(HomeworkNo);
    }*//*


    @Transactional
    public int createHomework(HomeworkDto homeworkDto, String userCode){

        return homeworkMapper.createHomework(homeworkDto);
    }
    @Transactional
    public int deleteHomework(Long homeworkNo){
        return homeworkMapper.deleteHomework(homeworkNo);
    }

    @Transactional
    public int updateHomework(HomeworkDto homeworkDto){
        return homeworkMapper.updateHomework(homeworkDto);
    }

}
*/
