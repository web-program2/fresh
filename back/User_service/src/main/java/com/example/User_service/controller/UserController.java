package com.example.User_service.controller;

import com.example.User_service.service.UserService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user-service")
public class UserController {

    private UserService userService;





    @PostMapping("/check_id")
    public boolean duplicatedId(@RequestBody String id){
        System.out.println(id);
        return true;
    }


}
