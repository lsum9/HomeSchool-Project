package com.home_school.teacher.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ClassDto {
    private Long classNo;
    private Long teacherUserNo;
    private int subjectNo;
    private String subjectTitle;
    private String classTitle;
    private int classMemberNum;
    private String classStrtDt;
    private String classEndDt;
    private String classStatus;
    private int joinCnt;

    private String userCode;
}
