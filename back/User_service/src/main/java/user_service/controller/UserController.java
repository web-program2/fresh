package user_service.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import user_service.dto.UserCatalogDto;
import user_service.dto.input.InputData;
import user_service.dto.input.LoginInputDto;
import user_service.dto.output.LoginOutputDto;
import user_service.jpa.User;
import user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user-service", method = {RequestMethod.GET, RequestMethod.POST})
public class UserController {
    private final UserService userService;

    @GetMapping(value="/zipkin")
    public String zipkinService1(){
        log.info("Inside zipkinService 1..");
        return "Hello?";
    }
    @PostMapping("/sign_in")
    public LoginOutputDto signIn(@RequestBody LoginInputDto loginInputDto) throws Exception {
        LoginOutputDto res = null;
        System.out.println(loginInputDto.getIsForce());
        try {
            res = userService.signIn(loginInputDto.getId(), loginInputDto.getPw(), loginInputDto.getIsForce());
            return res;
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.ACCEPTED, e.getMessage());
        }
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
        System.out.println(data.getNickName());
        System.out.println(res);
        return res;
    }

//    public User getUserData(){
//
//    }

    @PostMapping("/all")
    public UserCatalogDto testAll(){
        int temp = 1;
        UserCatalogDto userDto = userService.testAll(temp);
        return userDto;
    }

    @PostMapping("/send_mail")
    public boolean sendMail(@RequestBody InputData inputData) throws MessagingException {
        return userService.sendMail(inputData.getEmail());
    }


    @PostMapping("/check_mail_no")
    public boolean checkMailNo(@RequestBody InputData inputData){
        System.out.println(inputData.getNo());
        return userService.checkMailNo(inputData.getEmail(), inputData.getNo());
    }

    @PostMapping("/response_user")
    public User getResponseUser(@RequestBody Long userIdx){
        User user = userService.getUser(userIdx);
        return user;
    }

}
