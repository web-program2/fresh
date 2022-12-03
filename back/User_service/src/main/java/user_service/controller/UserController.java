package user_service.controller;

import user_service.dto.input.InputData;
import user_service.dto.input.LoginInputDto;
import user_service.dto.output.LoginOutputDto;
import user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user-service")
public class UserController {
    private final UserService userService;

    @PostMapping("/sign_in")
    public LoginOutputDto signIn(@RequestBody LoginInputDto loginInputDto){
        System.out.println(loginInputDto);
        LoginOutputDto res = userService.signIn(loginInputDto.getId(), loginInputDto.getPw(), loginInputDto.isForce());
        return res;
    }
    @PostMapping("/sign_up")
    public boolean signUp(@RequestBody InputData data){
        String id = data.getId();
        String pw = data.getPw();
        String email = data.getEmail();
        String nickName = data.getNickName();
        String role = data.getRole();
        boolean res = userService.signUp(id, pw, email, nickName, role);
        return res;
    }

    @PostMapping("/check_id")
    public boolean duplicatedId(@RequestBody InputData data){
        boolean res = userService.duplicatedId(data.getId());
        return res;
    }

    @PostMapping("/check_nickname")
    public boolean duplicatedNickName(@RequestBody InputData data){
        boolean res = userService.duplicatedNickName(data.getNickName());
        return res;
    }

//    public User getUserData(){
//
//    }



}
