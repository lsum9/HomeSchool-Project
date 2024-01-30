package com.home_school.admin.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class CategoryDto {
    private String categoryType;
    private int subjectNo;
    private String subjectTitle;
    private int areaNo;
    private String areaTitle;
    private int subareaNo;
    private String subareaTitle;
}
