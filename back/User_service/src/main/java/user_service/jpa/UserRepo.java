package user_service.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class UserRepo{

    private final EntityManager em;

    public List<User> findByUserId(String id){
        return em.createQuery("select m from User m where m.id = :id", User.class)
                .setParameter("id", id)
                .getResultList();
    }

    public List<User> findByUserNickName(String nickName) {
        return em.createQuery("select m from User m where m.nickName = :nickName", User.class)
                .setParameter("nickName", nickName)
                .getResultList();
    }


    public List<User> findAll() {
        return em.createQuery("select e from User e", User.class).getResultList();
    }

    public User findByUserIdPw(String id, String pw) {
        List<User> userList = findAll();
        System.out.println(userList);
        User res = new User();
        for(int i =0; i < userList.size(); i++){
            User user = userList.get(i);
            if(user.getId().equals(id) && user.getPw().equals(pw)){
                res =  user;
            }
        }
        return res;
    }



    ///////////////////UserToken 관련)
    public List<UserToken> findUserToken(long userIdx){
        return em.createQuery("select m from UserToken m where m.userIdx = :userIdx", UserToken.class)
                .setParameter("userIdx", userIdx)
                .getResultList();
    }
    public void updateUserToken(Long userIdx, String accessToken) {
        em.createQuery(
                "UPDATE UserToken u set u.token = :token where u.userIdx = :userIdx")
                .setParameter("userIdx", userIdx)
                .setParameter("token", accessToken)
                .executeUpdate();
    }


    public List<User> getUserByIdx(long userIdx) {
        return em.createQuery("select m from User m where m.userIdx = :userIdx", User.class)
                .setParameter("userIdx", userIdx)
                .getResultList();
    }

    public void createEmailNo(String email, String no) {
        em.createNativeQuery("INSERT INTO email_nos (email, no) VALUES (?,?)")
                .setParameter(1, email)
                .setParameter(2, no)
                .executeUpdate();
    }

    public void createUser(User user) {
        em.persist(user);
    }

    public void createUserToken(Long userIdx) {
        em.createNativeQuery("INSERT INTO user_tokens (userIdx, token) VALUES (?,?)")
                .setParameter(1, userIdx)
                .setParameter(2,"")
                .executeUpdate();
    }

    public List<User> findByUserEmail(String email) {
        return em.createQuery("select m from User m where m.email = :email", User.class)
                .setParameter("email", email)
                .getResultList();
    }

    public List<EmailNo> getEmailNosByEmail(String email) {
        return em.createQuery("select m from EmailNo m where m.email = :email", EmailNo.class)
                .setParameter("email", email)
                .getResultList();
    }

    public void deleteEmailNosByEmail(String email) {
        em.createQuery("DELETE from EmailNo e where e.email = :email")
                .setParameter("email", email)
                .executeUpdate();
    }
}