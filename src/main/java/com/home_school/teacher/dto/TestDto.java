package com.home_school.teacher.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class TestDto {
    private long examNo;

    private long testNo;
    private long classNo;
    private String testTitle;
    private String testType;
    private List<Map<String, Long>> testTarget;
    private Long testTargetNo;

    private String userCode;


}
