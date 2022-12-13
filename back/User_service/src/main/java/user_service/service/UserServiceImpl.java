package user_service.service;

import lombok.RequiredArgsConstructor;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import user_service.client.CatalogServiceClient;
import user_service.dto.UserCatalogDto;
import user_service.dto.output.LoginOutputDto;
import user_service.jpa.UserRepo;
import org.springframework.stereotype.Service;
import user_service.jpa.Users;
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

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Override
    public boolean duplicatedId(String id) {
        List<Users> users = userRepo.findByUserId(id);
        boolean flag = users.size() == 0 ? false : true;
        return flag;
    }
    @Override
    public boolean duplicatedNickName(String nickName) {
        List<Users> users = userRepo.findByUserNickName(nickName);
        boolean flag= users.size() == 0 ? false : true;
        return flag;
    }

    @Override
    public LoginOutputDto signIn(String id, String pw, boolean isForce) {
        return null;
    }

    @Override
    public boolean signUp(String id, String pw, String email, String nickName, String role) {
//        userRepo.saveUser();
        return true;
    }

    @Override
    public UserCatalogDto testAll(int userIdx) {
        UserCatalogDto userCatalogDto = new UserCatalogDto();
        Users users = userRepo.getUserByIdx(userIdx).get(0);
        userCatalogDto.setUsers(users);
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
