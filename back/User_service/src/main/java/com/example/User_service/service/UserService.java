package com.example.User_service.service;


import com.example.User_service.dto.output.LoginOutDto;

public interface UserService {

    public boolean duplicatedId(String id);
    public boolean duplicatedNickName(String nickName);
    public LoginOutDto signIn(String id, String pw);



}
