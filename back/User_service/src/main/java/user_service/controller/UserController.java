package user_service.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import user_service.dto.UserCatalogDto;
import user_service.dto.input.InputData;
import user_service.dto.input.LoginInputDto;
import user_service.dto.output.LoginOutputDto;
import user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


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

    @PostMapping("/token")
    public String createToken(){
        Date now = new Date();
        return Jwts.builder()
                .setSubject("aaaa") // 보통 username
                .setHeader(createHeader())
//                .setClaims(createClaims(member)) // 클레임, 토큰에 포함될 정보
                .setExpiration(new Date(now.getTime() + 3600)) // 만료일
                .signWith(SignatureAlgorithm.HS256, "aaaa")
                .compact();
    }
    private Map<String, Object> createHeader() {
        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        header.put("alg", "HS256"); // 해시 256 사용하여 암호화
        header.put("regDate", System.currentTimeMillis());
        return header;
    }
}
