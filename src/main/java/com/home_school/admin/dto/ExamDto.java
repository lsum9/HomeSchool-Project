package com.home_school.admin.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ExamDto {
    private Long examNo;
    private int gradeNo;
    private int subjectNo;
    private int areaNo;
    private int subareaNo;
    private int userNo;
    private String examType;
    private String examTitle;
    private int examTotPoint;
    private String examTime;
    private String frstWrtId;
    private int examQcnt;
    private int totalRow;

    private Map<Integer,Long> examQuestionNo;
}
