package com.home_school.teacher.dto;

import lombok.Data;

@Data
public class LectureDto {
    private Long lectureNo;
    private Long classNo;
    private String lectureTitle;
    private String lectureFile;
    private String lectureStatus;
    private String frstWrtDt;
    private String lastWrtDt;

    private String userCode;
}
