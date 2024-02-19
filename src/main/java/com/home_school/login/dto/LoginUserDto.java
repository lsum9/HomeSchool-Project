package com.home_school.login.dto;

import lombok.Data;

@Data
public class LoginUserDto {
    private String userId;
    private String userType;
    private String token;
    private String userName;
    private String userEmail;
    private String profileText;

    private Long userNo;
    private String userStatus;
    private String userCode;

    private String sub;
    private String email;
    private String name;
    private String picture;

}
