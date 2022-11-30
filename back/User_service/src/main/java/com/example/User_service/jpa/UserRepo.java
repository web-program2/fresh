package com.example.User_service.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class UserRepo{

    private final EntityManager em;

    public ArrayList<User> findByUserId(String id){
        //findAll().stream() 루프를 돌면서 찾는다\
        System.out.println(id);
        ArrayList<User> res = new ArrayList<>();
        List<User> userList = findAll();
        for(int i = 0; i < userList.size(); i++){
//            System.out.println(userList.get(i).getId() + " = " + id);
//            System.out.println(userList.get(i).getId().equals(id));
            if(userList.get(i).getId().equals(id)){
                System.out.println("아룸ㄴ아ㅣ루나ㅣ라ㅜ미라ㅣㄴㅁ라ㅣㄴ");
                res.add(userList.get(i));
            }
        }
        return res;
    }
//    public List<User> findByUserId(String id) {
//        return em.createQuery("select m from User m where m.id = :id", User.class)
//                .setParameter("id", id)
//                .getResultList();
//    }

    public List<User> findAll() {
        return em.createQuery("select e from User e", User.class).getResultList();
    }

}