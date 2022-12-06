package user_service.service;

import lombok.RequiredArgsConstructor;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import user_service.client.CatalogServiceClient;
import user_service.dto.UserCatalogDto;
import user_service.dto.output.LoginOutputDto;
import user_service.jpa.User;
import user_service.jpa.UserRepo;
import user_service.jpa.UserToken;
import org.springframework.stereotype.Service;
import user_service.vo.ResponseCatalog;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepo userRepo;
    private final CatalogServiceClient catalogServiceClient;
    private final JavaMailSender mailSender;

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

    @Override
    public UserCatalogDto testAll(int userIdx) {
        UserCatalogDto userCatalogDto = new UserCatalogDto();
        User user = userRepo.getUserByIdx(userIdx).get(0);
        userCatalogDto.setUser(user);
        System.out.println(userIdx);
        List<ResponseCatalog> responseCatalog = catalogServiceClient.testAll(userIdx);
        userCatalogDto.setCatalogList(responseCatalog);
        return userCatalogDto;
    }

    @Override
    public boolean sendMail() {
        try {
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper mailHelper = new MimeMessageHelper(mail,false,"UTF-8");

            mailHelper.setTo("lch010201@naver.com");
            mailHelper.setSubject("test1");
            mailHelper.setText("content입니당", true);

            mailSender.send(mail);
            return true;
        }catch(MessagingException e) {
            throw new RuntimeException();
        }

    }

}
