package com.home_school.admin.dto;


import lombok.Data;

@Data
public class UserDto {
    private Long userNo;
    private String userId;
    private String userType;
    private String userStatus;
    private String frstWrtDt;
    private String profileName;
    private String profilePhone;
    private int totalRow;
}
