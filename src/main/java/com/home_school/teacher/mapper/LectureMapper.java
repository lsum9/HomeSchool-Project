package com.home_school.teacher.mapper;

import com.home_school.teacher.dto.LectureDto;
import com.home_school.util.paging.PagingVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LectureMapper {
    List<LectureDto> readLectureList(PagingVo pagingVo);

    //List<LectureDto> readLectureDetail(Long LectureNo);
    int lectureCnt(PagingVo pagingVo);
    int createLecture(LectureDto lectureDto);
    int deleteLecture(Long lectureNo);
    int updateLecture(LectureDto lectureDto);
    
}
