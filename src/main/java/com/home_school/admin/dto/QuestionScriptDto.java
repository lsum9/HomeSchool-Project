package com.home_school.admin.dto;

import lombok.Data;

@Data
public class QuestionScriptDto {
    private int scriptNo;
    private String scriptTitle;
    private String scriptImage;
    private String frstWrtDt;
    private String frstWrtId;
    private String lastWrtDt;
    private String lastWrtId;
    private int gradeNo;
    private int subareaNo;
    private int areaNo;
    private int subjectNo;
}
