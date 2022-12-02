package com.example.User_service.service;


import com.example.User_service.dto.output.LoginOutputDto;

public interface UserService {

    public boolean duplicatedId(String id);
    public boolean duplicatedNickName(String nickName);
    public LoginOutputDto signIn(String id, String pw, boolean isForce);
    public boolean signUp(String id,String pw,String email,String nickName,String role);

}
