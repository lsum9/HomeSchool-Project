package com.home_school.login.dto;

import lombok.Data;

@Data
public class TokenDto {
    private String userCode;
    private String accessToken;
    private String refreshToken;
}
