package com.home_school.login.dto;

import lombok.Data;

@Data
public class UserDto {
    private String userId;
    private String userType;
    private String token;
    private String userName;
    private String userEmail;

    private Long userNo;
    private String userStatus;

    private String userCode;
}
