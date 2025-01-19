package com.tekarchusers.tafusersms.services;

import com.tekarchusers.tafusersms.models.Users;
import com.tekarchusers.tafusersms.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


@Service
public class UserServiceImp implements UserService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${datastore.user.service.url}")
    private String dataStore_User_Url;

    @Override
    public Users registerUser(Users user) {

        return restTemplate.postForObject(dataStore_User_Url + "register", user, Users.class);
    }

    @Override
    public ResponseEntity<Object> getUserById(Long userId) {
    Users user = restTemplate.getForObject(dataStore_User_Url + userId, Users.class);
        if (user != null) {

            return ResponseEntity.ok(user);
        } else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found " + userId);
        }

    }

    public void updateUser(Long userId, Users user){
        try{
            restTemplate.put(dataStore_User_Url + userId,user);
        } catch (HttpClientErrorException  e) {
            System.out.println("Error: " + e.getStatusCode());
        }

    }

}
