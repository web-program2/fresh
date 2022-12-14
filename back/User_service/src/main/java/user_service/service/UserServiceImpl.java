package user_service.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.transaction.annotation.Transactional;
import user_service.client.CatalogServiceClient;
import user_service.dto.UserCatalogDto;
import user_service.dto.output.LoginOutputDto;
import user_service.jpa.EmailNo;
import user_service.jpa.User;
import user_service.jpa.UserRepo;
import user_service.jpa.UserToken;
import org.springframework.stereotype.Service;
import user_service.vo.ResponseCatalog;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.Duration;
import java.util.*;


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

    @Transactional
    @Override
    public LoginOutputDto signIn(String id, String pw, boolean isForce) throws Exception {
        // 1. id pw로 user 찾아오기
        User user = userRepo.findByUserIdPw(id, pw);

        if(user.getUserIdx() == null){
            throw new Exception("not found");
        }
        System.out.println(isForce);
        if(!isForce){
            // 2. 해당 유저가 로그인되어져 있는지 확인해보기
            List<UserToken> userTokenList = userRepo.findUserToken(user.getUserIdx());
            UserToken userToken = userTokenList.get(0);
            if(userToken.getToken().length() > 0){
                throw new Exception("is login");
            }
        }

        // 3. 토큰을 발급.
        String accessToken = this.generateAccessToken(user);
        String refreshToken = this.generateRefreshToken();
        // 3. 해당 로그인 하는 유저의 토큰을 저장
        userRepo.updateUserToken(user.getUserIdx(), accessToken);

        //4. 어세스, 리프레쉬 토큰을 리턴해주기
        LoginOutputDto res = new LoginOutputDto();
        res.setAccessToken(accessToken);
        res.setRefreshToken(refreshToken);
        return res;
    }

    @Transactional
    @Override
    public boolean signUp(String id, String pw, String email, String nickName, String role) {
        User user = new User();
        user.setId(id);
        user.setPw(pw);
        user.setEmail(email);
        user.setNickName(nickName);
        user.setRole(role);
        userRepo.createUser(user);
        User resUser = userRepo.findByUserIdPw(id, pw);
        userRepo.createUserToken(resUser.getUserIdx());
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
    public User getUser(Long userIdx) {
        User user = userRepo.getUserByIdx(userIdx).get(0);
        return user;
    }

    @Transactional
    @Override
    public boolean sendMail(String email) {
        List<User> userList = userRepo.findByUserEmail(email);
        if(userList.size() != 0){
            return false;
        }
        try {
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper mailHelper = new MimeMessageHelper(mail,false,"UTF-8");
            String randomNo = generateAuthentication();
            mailHelper.setTo(email);
            mailHelper.setSubject("Fresh 인증번호");
            String text = "Fresh 인증번호 : " + randomNo + " 입니다. ";
            mailHelper.setText(text, true);
            mailSender.send(mail);
            userRepo.createEmailNo(email, randomNo);
            return true;
        }catch(MessagingException e) {
            throw new RuntimeException();
        }
    }
    @Transactional
    @Override
    public boolean checkMailNo(String email, String no) {
        List<EmailNo> emailNoList = userRepo.getEmailNosByEmail(email);
        EmailNo emailNo = emailNoList.get(emailNoList.size()-1);
        if(emailNo.getNo().equals(no)){
            userRepo.deleteEmailNosByEmail(email);
            return true;
        }else {
            return false;
        }
    }


    public String generateAuthentication(){
        Random random = new Random();
        String ranNum ="";
        for (int i = 0; i < 6; i++) {
            String ran = Integer.toString(random.nextInt(10));
            if (!ranNum.contains(ran)) { //중복체크
                ranNum += ran;
            } else {
                i -= 1;
            }
        }
        return ranNum;
    }

    ////////////////// Token part //////////////////
    private String generateAccessToken(User user){
        Date now = new Date();
        Long expiredTime = 1000 * 60L * 60L * 3L;//3시간
        Claims claims= Jwts.claims();
        claims.put("userIdx", user.getUserIdx());
        claims.put("id", user.getId());
        claims.put("nickName", user.getNickName());
        claims.put("email", user.getEmail());
        claims.put("role", user.getRole());


        return Jwts.builder()
                .setHeader(createHeader())
                .setClaims(claims) // 클레임, 토큰에 포함될 정보
                .setExpiration(new Date(now.getTime() + expiredTime)) // 만료일
                .signWith(SignatureAlgorithm.HS256, "aaaa")
                .compact();
    }
    private String generateRefreshToken(){
        Date now = new Date();
        long expiredTime = Duration.ofDays(14).toMillis();
        return Jwts.builder()
                .setHeader(createHeader())
                .setExpiration(new Date(now.getTime() + expiredTime)) // 만료일
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
