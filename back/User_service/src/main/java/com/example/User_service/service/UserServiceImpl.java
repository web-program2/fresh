package com.example.User_service.service;

import com.example.User_service.dto.output.LoginOutputDto;
import com.example.User_service.jpa.User;
import com.example.User_service.jpa.UserRepo;
import com.example.User_service.jpa.UserToken;
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
        List<User> user = userRepo.findByUserId(id);
        boolean flag = user.size() == 0 ? false : true;
        return flag;
    }


    @Override
    public boolean duplicatedNickName(String nickName) {
        List<User> user = userRepo.findByUserNickName(nickName);
        boolean flag= user.size() == 0 ? false : true;
        return flag;
    }

    @Override
    public LoginOutputDto signIn(String id, String pw, boolean isForce) {
        // 1. id pw로 user 찾아오기
        User user = userRepo.findByUserIdPw(id, pw);

        if(user == null){
            throw new Error("not found");
        }
        // login을 이미 돌렸을 경우
        if(!isForce){
            // 2. 해당 유저가 로그인되어져 있는지 확인해보기
            List<UserToken> userTokenList = userRepo.findUserToken(user.getUserIdx());
            UserToken userToken = userTokenList.get(0);
            if(userToken.getToken().length() > 0){
                throw new Error("is login");
            }
        }

        // 3. pw와 같은 민감한 데이터는 삭제 후 토큰을 발급.
        user.setPw("");
//        String accessToken = jwtService.sign(user);
//        String refreshToken = jwtService.sign();
        String accessToken = "aa";
        String refreshToken = "bbb";
        // 3. 해당 로그인 하는 유저의 토큰을 저장
        userRepo.updateUserToken(user.getUserIdx(), accessToken);

        //4. 어세스, 리프레쉬 토큰을 리턴해주기
        LoginOutputDto res = new LoginOutputDto();
        res.setAccessToken(accessToken);
        res.setRefreshToken(refreshToken);
        return res;
    }

    @Override
    public boolean signUp(String id, String pw, String email, String nickName, String role) {
//        userRepo.saveUser();
        return true;
    }
}
