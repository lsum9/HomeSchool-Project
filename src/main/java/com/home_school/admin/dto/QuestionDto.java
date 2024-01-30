package com.home_school.admin.dto;

import lombok.Data;

@Data
public class QuestionDto {
    private int questionNo;
    private int scriptNo;
    private int areaNo;
    private int subareaNo;
    private int subjectNo;
    private String questionImage;
    private int questionAnswer;
    private int questionPoint;
    private String frstWrtDt;
    private String frstWrtId;
    private String lastWrtDt;
    private String lastWrtId;
    private int questionNum;
    private String questionTitle;
    private String questionWriterType;
}
