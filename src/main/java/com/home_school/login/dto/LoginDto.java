package com.home_school.login.dto;

import lombok.Data;

@Data
public class LoginDto {
    private String userCode;
    private String token;
    private String refreshToken;
}
