package com.example.User_service.dto.output;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginOutDto {
    private String accessToken;
    private String refreshToken;

}
