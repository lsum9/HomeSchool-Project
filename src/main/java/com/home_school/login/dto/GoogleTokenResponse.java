package com.home_school.login.dto;

import lombok.Data;

@Data
public class GoogleTokenResponse {
    private String name;
    private String email;
    private String sub;
}
