package user_service.service;


import org.springframework.stereotype.Service;
import user_service.dto.UserCatalogDto;
import user_service.dto.output.LoginOutputDto;

@Service
public interface UserService {

    public boolean duplicatedId(String id);
    public boolean duplicatedNickName(String nickName);
    public LoginOutputDto signIn(String id, String pw, boolean isForce);
    public boolean signUp(String id,String pw,String email,String nickName,String role);


    UserCatalogDto testAll(int userIdx);

    boolean sendMail();
}
