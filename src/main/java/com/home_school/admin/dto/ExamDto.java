package com.home_school.admin.dto;


import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
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
    private String frstWrtDt;
    private String frstWrtId;
    private int examQcnt;
    private int totalRow;
    
    //작업후 삭제요망
    private Map<Integer,Long> examQuestionNo;
}
