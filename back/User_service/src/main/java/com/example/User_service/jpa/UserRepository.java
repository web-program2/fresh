package com.example.User_service.jpa;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
//    UserEntity findByUserId(String userId);
}
