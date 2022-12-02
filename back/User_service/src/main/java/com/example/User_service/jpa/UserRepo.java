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

}