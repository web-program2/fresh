package com.example.User_service.dto;

import com.example.User_service.vo.ResponseOrder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserDto {
    private String email;
    private String pwd;
    private String name;
    private String userId;
    private Date createAt;
    private String encryptedPwd;

    private List<ResponseOrder> orders;
}
