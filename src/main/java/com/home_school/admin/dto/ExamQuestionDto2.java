package com.home_school.admin.dto;


import lombok.Data;

@Data
public class ExamQuestionDto2 {
    //private Long examNo;

    //private Long examQuestionNo;

    private int orderNo;
    private Long questionNo;
    private String questionTitle;
    private String questionImage;
    private int questionAnswer;
    private String questionAnswerImage;
    private int questionPoint;
    private int questionNum;
    private String questionWriterType;

    private Long scriptNo;
    private String scriptTitle;
    private String scriptImage;

    private int subjectNo;
    private int gradeNo;
    private int areaNo;
    private int subareaNo;
}
