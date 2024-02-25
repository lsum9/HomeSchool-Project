package com.home_school.teacher.service;

import com.home_school.teacher.dto.LectureDto;
import com.home_school.teacher.mapper.LectureMapper;
import com.home_school.util.paging.Paging;
import com.home_school.util.paging.PagingVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureService {
    private final LectureMapper lectureMapper;
    private final Paging paging;

    //총 글개수
    /*@Transactional(readOnly = true)
    public int lectureCnt(PagingVo pagingVo){
        pagingVo=paging.pagingInfo(pagingVo);
        return lectureMapper.lectureCnt(pagingVo);
    }*/

    @Transactional(readOnly = true)
    public List<LectureDto> readLectureList(PagingVo pagingVo){
        //총 로우수 pagingVo에 추가
        pagingVo.setTotalRow(lectureMapper.lectureCnt(pagingVo));
        //가공된 키워드, 현재페이지, 총 로우 수 담긴 pagingVo 페이징 클래스로 넘기기
        pagingVo=paging.pagingInfo(pagingVo);
        return lectureMapper.readLectureList(pagingVo);
    }

    /*@Transactional(readOnly = true)
    public Map<String,Object> readLectureDetail(Long LectureNo){
        return LectureMapper.readLectureDetail(LectureNo);
    }*/

    @Transactional
    public int createLecture(LectureDto lectureDto){
        return lectureMapper.createLecture(lectureDto);
    }
    @Transactional
    public int deleteLecture(Long lectureNo){
        return lectureMapper.deleteLecture(lectureNo);
    }

    @Transactional
    public int updateLecture(LectureDto lectureDto){
        return lectureMapper.updateLecture(lectureDto);
    }

}
