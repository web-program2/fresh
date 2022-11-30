package com.example.User_service.service;

import com.example.User_service.dto.output.LoginOutDto;
import com.example.User_service.jpa.User;
import com.example.User_service.jpa.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepo userRepo;

    @Override
    public boolean duplicatedId(String id) {
        System.out.println("찾으려는 id : " + id);
        List<User> user = userRepo.findByUserId(id);
        System.out.println(user);
        boolean flag = user.size() == 0 ? false : true;
        return flag;
//        return false;
    }


    @Override
    public boolean duplicatedNickName(String nickName) {
        return false;
    }

    @Override
    public LoginOutDto signIn(String id, String pw) {
        return null;
    }
}
