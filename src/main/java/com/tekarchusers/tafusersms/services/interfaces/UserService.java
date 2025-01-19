package com.tekarchusers.tafusersms.services.interfaces;

import com.tekarchusers.tafusersms.models.Users;
import org.springframework.http.ResponseEntity;

public interface UserService {
    Users registerUser(Users user);
    ResponseEntity<Object> getUserById(Long userId);

}
