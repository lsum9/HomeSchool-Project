package com.home_school.admin.dto;


import lombok.Data;

import java.util.List;

@Data
public class ExamQuestionDto {
    private Long examQuestionNo;
    private Long examNo;
    private Long questionNo;
    private String questionImage;
    private int orderNo;
    private String frstWrtDt;
    private String frstWrtId;
    private String lastWrtDt;
    private String lastWrtId;
}
