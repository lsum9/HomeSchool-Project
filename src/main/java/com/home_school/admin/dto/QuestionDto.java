package com.home_school.admin.dto;

import lombok.Data;

@Data
public class QuestionDto {
    //문항 기본정보
    private Long questionNo;
    private int gradeNo;
    private Long scriptNo;
    private int areaNo;
    private int subareaNo;
    private int subjectNo;
    private String questionImage;
    private int questionAnswer;
    private String questionAnswerImage;
    private int questionPoint;
    private int questionNum;
    private String questionTitle;
    private String questionWriterType;
    private int orderNo;
}
