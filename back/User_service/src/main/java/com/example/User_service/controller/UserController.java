package com.example.User_service.controller;

import com.example.User_service.dto.input.InputId;
import com.example.User_service.dto.input.LoginInputDto;
import com.example.User_service.dto.output.LoginOutDto;
import com.example.User_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user-service")
public class UserController {
    private final UserService userService;

    @PostMapping("/sign_in")
    public LoginOutDto signIn(@RequestBody LoginInputDto loginInputDto){
        System.out.println(loginInputDto);
        LoginOutDto res = userService.signIn(loginInputDto.getId(), loginInputDto.getPw());
        return res;
    }

    @PostMapping("/check_id")
    public boolean duplicatedId(@RequestBody InputId data){
        boolean res = userService.duplicatedId(data.getId());
        return res;
    }

    @PostMapping("/check_nickname")
    public boolean duplicatedNickName(@RequestBody String nickName){
        System.out.println(nickName);
        boolean res = userService.duplicatedNickName(nickName);
        return res;
    }



}
