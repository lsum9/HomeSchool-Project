package com.home_school.teacher.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class HomeworkDto {
    private long examNo;
    private int gradeNo;
    private long subjectNo;
    private long areaNo;
    private long subareaNo;
    private long userNo;
    private String examType;
    private String examTitle;
    private int examTotPoint;
    private String examTime;


    private long testNo;
    private long classNo;
    private String testTitle;
    private String testType;
}
