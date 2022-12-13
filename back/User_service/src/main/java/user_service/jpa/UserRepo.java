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
        em.createQuery();
    }


    public List<User> getUserByIdx(long userIdx) {
        return em.createQuery("select m from User m where m.userIdx = :userIdx", User.class)
                .setParameter("userIdx", userIdx)
                .getResultList();
    }

    public void createEmailNo(String email, String randomNo) {
        em.createQuery();
    }
}