package com.example.User_service.service;

import com.example.User_service.client.OrderServiceClient;
import com.example.User_service.dto.UserDto;
import com.example.User_service.jpa.UserEntity;
import com.example.User_service.jpa.UserRepository;
import com.example.User_service.vo.ResponseOrder;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    BCryptPasswordEncoder passwordEncoder;
    OrderServiceClient orderServiceClient;
    Environment env;
    RestTemplate restTemplate;

    @Autowired
    public UserServiceImpl(Environment env,
                           UserRepository userRepository,
                           BCryptPasswordEncoder passwordEncoder,
                           RestTemplate restTemplate,
                           OrderServiceClient orderServiceClient) {
        this.env = env;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.restTemplate = restTemplate;
        this.orderServiceClient = orderServiceClient;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserEntity userEntity = mapper.map(userDto, UserEntity.class);
        userEntity.setEncryptedPwd("encrypted_password");
        userRepository.save(userEntity);

        return null;
    }

    @Override
    public UserDto getUserByUserId(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        UserDto userDto = new ModelMapper().map(userEntity, UserDto.class);

//        String orderUrl = String.format("http://localhost:8000/order-service/%s/orders", userId);
//
//        ResponseEntity<List<ResponseOrder>> orderListResponse =
//                restTemplate.exchange(orderUrl, HttpMethod.GET, null,
//                        new ParameterizedTypeReference<List<ResponseOrder>>() {
//                        });
//
//        List<ResponseOrder> orderList = orderListResponse.getBody();

        List<ResponseOrder> orderList = orderServiceClient.getOrders(userId);
        userDto.setOrders(orderList);
        return userDto;
    }

    @Override
    public Iterable<UserEntity> getUserByAll() {
        return userRepository.findAll();
    }
}
