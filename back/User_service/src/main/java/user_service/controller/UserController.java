package user_service.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import user_service.dto.JwtRequest;
import user_service.dto.JwtResponse;
import user_service.dto.UserCatalogDto;
import user_service.dto.input.InputData;
import user_service.dto.input.LoginInputDto;
import user_service.dto.output.LoginOutputDto;
import user_service.jpa.Users;
import user_service.jwt.JwtTokenUtil;
import user_service.jwt.JwtUserDetailsService;
import user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;


@RestController
//@RequiredArgsConstructor
@RequestMapping("/user-service")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailService;

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
    @PostMapping("/authenticate")
    public String createAuthenticationToken() throws Exception {
        Users users = new Users();
        users.setId("qq");
        users.setEmail("ASdfsdf");
        users.setNickName("ADsfds");
        users.setRole("Asdfs");

        String token = jwtTokenUtil.generateToken(users);
        System.out.println(token);

        return token;
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

    @PostMapping("/all")
    public UserCatalogDto testAll(){
        int temp = 1;
        UserCatalogDto userDto = userService.testAll(temp);
        return userDto;
    }

    @GetMapping("/sendMail")
    public boolean sendMail() throws MessagingException {

        return userService.sendMail();
    }

}
